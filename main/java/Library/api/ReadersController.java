package Library.api;

import Library.model.Issue;
import Library.model.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequestMapping("/reader")
public class ReadersController {
    @Autowired
    private final ReaderService service;

    ReadersController(ReaderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Reader>> getAll() {
        try {
            List<Reader> reader = service.getAll();
            return ResponseEntity.ok(reader);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reader> getReader(@PathVariable Integer id) {
        try {
            Reader reader = service.getById(id);
            return ResponseEntity.ok(reader);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/issue")
    public ResponseEntity<List<Issue>> getIssues(@PathVariable Integer id) {
        try {
            List<Issue> issues = service.getReaderIssues(id);
            return ResponseEntity.ok(issues);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Objects> delete(@PathVariable Integer id) {
        try {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Reader> add(@RequestBody Reader reader) {
        return ResponseEntity.ok(service.add(reader));
    }
}