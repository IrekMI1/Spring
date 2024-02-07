package Library.api;

import Library.model.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/book")
@Tag(name = "Book")
public class BooksController {
    private final BookService bookService;

    public BooksController(BookService books) {
        this.bookService = books;
    }

    @GetMapping
    @Operation(summary = "get all books", description = "Возвращает все доступные книги в библиотеке")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "not found") })
    public ResponseEntity<List<Book>> getAll() {
        try {
            List<Book> books = bookService.getAll();
            return ResponseEntity.ok(books);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "get book by ID", description = "Возвращает книгу по заданному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "book not found")
    })
    public ResponseEntity<Book> getBook(@PathVariable Integer id) {
        try {
            Book book = bookService.getById(id);
            return ResponseEntity.ok(book);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete book by ID", description = "Удаляет книгу по заданному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "book not found")
    })
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        try {
            bookService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "add new book", description = "Добавляет книгу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "book not found")
    })
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return ResponseEntity.ok(savedBook);
    }
}