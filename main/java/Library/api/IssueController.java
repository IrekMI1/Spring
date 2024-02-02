package Library.api;

import Library.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/issue")
public class IssueController {
    @Autowired
    private IssueService service;

    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
        System.out.printf("Получен запрос на выдачу: readerId=%s, bookId=%d \n", request.getReaderId(), request.getBookId());

        Issue issue = null;
        try {
            issue = service.issue(request);
            return ResponseEntity.ok(issue);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(issue);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Issue> returnBook(@PathVariable Integer id) {
        System.out.println("Получен запрос на возврат книги, номер записи: " + id);
        Issue issue;
        try {
            issue = service.returnBook(id);
            return ResponseEntity.ok(issue);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Issue>> getIssues() {
        List<Issue> issues;
        try {
            issues = service.getAll();
            return ResponseEntity.ok(issues);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{issueId}")
    public ResponseEntity<Issue> getIssue(@PathVariable Integer issueId) {
        Issue issue;
        try {
            issue = service.getIssue(issueId);
            return ResponseEntity.ok(issue);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}