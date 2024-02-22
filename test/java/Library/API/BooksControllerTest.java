package Library.API;

import Library.model.Book;
import Library.repository.BooksRepository;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;

public class BooksControllerTest extends JUnitSpringBootBase {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Data
    static class JUnitBookResponse {
        private Long id;
        private String name;
        private String description;
    }

    @Test
    void testFindByIdSuccess() {
        Book expected = booksRepository.save(new Book("testBook", "test description"));
        System.out.println(expected);
        JUnitBookResponse responseBody = webTestClient.get()
                .uri("/book/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(JUnitBookResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getName(), responseBody.getName());
        Assertions.assertEquals(expected.getDescription(), responseBody.getDescription());
    }

    @Test
    void testFindByIdNotFound() {
        Long maxId = jdbcTemplate.queryForObject("select max(id) from books", Long.class);
        webTestClient.get()
                .uri("/book/" + maxId)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testGetAll() {
        booksRepository.saveAll(List.of(
                new Book("book1", "description1"),
                new Book("book2", "description2"),
                new Book("book1", "description1")
        ));

        List<Book> expected = booksRepository.findAll();

        List<JUnitBookResponse> responseBody = webTestClient.get()
                .uri("book")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<JUnitBookResponse>>() {
                })
                .returnResult()
                .getResponseBody();

        Assertions.assertEquals(expected.size(), responseBody.size());
        for (JUnitBookResponse bookResponse : responseBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), bookResponse.getId()))
                    .anyMatch(it -> Objects.equals(it.getName(), bookResponse.getName()));
            Assertions.assertTrue(found);
        }
    }

    @Test
    void testSave() {
        JUnitBookResponse request = new JUnitBookResponse();
        JUnitBookResponse responseBody = webTestClient.post()
                .uri("/book")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(JUnitBookResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertNotNull(responseBody.getId());
        Assertions.assertTrue(booksRepository.findById(request.getId()).isPresent());
    }
}
