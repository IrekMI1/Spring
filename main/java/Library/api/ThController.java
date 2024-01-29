package Library.api;

import Library.model.Book;
import Library.repository.BooksRepository;
import Library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ui")
public class ThController {
    @Autowired
    private IssueService issueService;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private ReaderService readerService;
    private final BooksRepository booksRepository;

    public ThController(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", booksRepository.getAll());
        return "books";
    }

    @GetMapping("/reader")
    public String getReader(Model model) {
        model.addAttribute("readers", readerRepository.getAll());
        return "readers";
    }

    @GetMapping("/reader/{id}")
    public String getReaderInfo(@PathVariable long id, Model model) {
        List<Book> books = readerService.getReaderIssues(id).stream()
                .map(it -> booksRepository.getById(it.getBookId()))
                .collect(Collectors.toList());

        model.addAttribute("reader", readerRepository.getById(id));
        model.addAttribute("books", books);
        return "reader";
    }

    @GetMapping("/issues")
    public String getIssues(Model model) {
        model.addAttribute("issues", issueService.getAll());
        return "issues";
    }
}