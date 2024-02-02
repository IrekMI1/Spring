package Library.api;

import Library.model.Book;
import Library.repository.BooksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService {
    private final BooksRepository booksRepository;

    public BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> getAll() {
        List<Book> books = booksRepository.findAll();
        if (books.isEmpty()) {
            throw new NoSuchElementException();
        }
        return books;
    }

    public Book getById(Integer id) {
        Optional<Book> book = booksRepository.findById(id);
        if (book.isEmpty()) {
            throw new NoSuchElementException();
        }
        return book.get();
    }

    public void deleteById(Integer id) {
        if (booksRepository.existsById(id)) {
            booksRepository.deleteById(id);
        } else {
            throw new NoSuchElementException();
        }
    }

    public Book addBook(Book book) {
        return booksRepository.save(book);
    }
}
