package Library.api;

import Library.model.Book;
import Library.repository.BooksRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BooksController {
    private final BooksRepository books;

    public BooksController(BooksRepository books) {
        this.books = books;
    }

    @GetMapping
    public List<Book> getBooks() {
        return books.getAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable long id) {
        return books.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        books.delete(id);
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
        books.add(book);
    }
}