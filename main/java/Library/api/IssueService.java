package Library.api;

import Library.model.Issue;
import Library.repository.BooksRepository;
import Library.repository.IssueRepository;
import Library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class IssueService {
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private BooksRepository booksRepository;

    @Value("${application.max-allowed-books: #{1}}")
    private long maxAllowedBooks;

    public Issue issue(IssueRequest request) {
        if (booksRepository.getById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором " + request.getBookId() + "\n");
        }
        if (readerRepository.getById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором " + request.getReaderId() + "\n");
        }
        if (issueRepository.getAll().stream()
                .filter(it -> Objects.equals(it.getReaderId(), request.getReaderId())
                        && it.getReturned_at() == null)
                .toList().size() >= maxAllowedBooks) {
            throw new IllegalStateException("Нельзя получить более " + maxAllowedBooks + " книг");
        }
        Issue issue = new Issue(request.getBookId(), request.getReaderId());
        issueRepository.save(issue);
        return issue;
    }

    public Issue getIssue(long id) {
        Issue issue = issueRepository.getById(id);
        if (issue == null) {
            throw new NoSuchElementException("Запись с номером " + id + " не найдена \n");
        }
        return issue;
    }

    public List<Issue> getAll() {
        List<Issue> issues = issueRepository.getAll();
        if (issues.isEmpty()) {
            throw new NoSuchElementException("Нет записей");
        }
        return issues;
    }

    public Issue returnBook(long id) {
        Issue issue = issueRepository.getById(id);
        if (issue == null) {
            throw new NoSuchElementException("Нет записи о получении книги.");
        }
        issue.setReturned_at(LocalDateTime.now());
        return issue;
    }
}