package Library.repository;

import Library.model.Book;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BooksRepository {

    private List<Book> books;

    public BooksRepository() {
        books = new ArrayList<>();
    }

    @PostConstruct
    void generateBooks() {
        books.addAll(List.of(
                new Book("События", "про жизнь"),
                new Book("Приключения", "про приключения"),
                new Book("Взлеты и падения", "про достижения"),
                new Book("Вечность", "про время")));
    }

    public List<Book> getAll() {
        return books;
    }

    public Book getById(long id) {
        return books.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public void delete(long id) {
        books.removeIf(it -> Objects.equals(it.getId(), id));
    }

    public void add(Book book) {
        books.add(book);
    }
}
