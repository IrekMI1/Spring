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
import java.util.Optional;

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
        if (booksRepository.findById(request.getBookId()).isEmpty()) {
            throw new NoSuchElementException("Не найдена книга с идентификатором " + request.getBookId() + "\n");
        }
        if (readerRepository.findById(request.getReaderId()).isEmpty()) {
            throw new NoSuchElementException("Не найден читатель с идентификатором " + request.getReaderId() + "\n");
        }
        if (issueRepository.findAll().stream()
                .filter(it -> Objects.equals(it.getReaderId(), request.getReaderId())
                        && it.getReturned_at() == null)
                .toList().size() >= maxAllowedBooks) {
            throw new IllegalStateException("Нельзя получить более " + maxAllowedBooks + " книг");
        }
        Issue issue = new Issue(request.getBookId(), request.getReaderId());
        issueRepository.save(issue);
        return issue;
    }

    public Issue getIssue(Integer id) {
        Optional<Issue> issue = issueRepository.findById(id);
        if (issue.isEmpty()) {
            throw new NoSuchElementException("Запись с номером " + id + " не найдена \n");
        }
        return issue.get();
    }

    public List<Issue> getAll() {
        List<Issue> issues = issueRepository.findAll();
        if (issues.isEmpty()) {
            throw new NoSuchElementException("Нет записей");
        }
        return issues;
    }

    public Issue returnBook(Integer id) {
        Optional<Issue> issueOpt = issueRepository.findById(id);
        if (issueOpt.isEmpty()) {
            throw new NoSuchElementException("Нет записи о получении книги.");
        }
        Issue issue = issueOpt.get();
        issue.setReturned_at(LocalDateTime.now());
        issueRepository.save(issue);
        return issue;
    }
}