package Library.api;

import Library.model.Issue;
import Library.model.Reader;
import Library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReadersController {
    @Autowired
    private ReaderService service;
    private ReaderRepository readers;

    ReadersController(ReaderRepository users) {
        this.readers = users;
    }

    @GetMapping
    public List<Reader> getAll() {
        return readers.getAll();
    }

    @GetMapping("/{id}")
    public Reader getReader(@PathVariable long id) {
        return readers.getById(id);
    }

    @GetMapping("/{id}/issue")
    public List<Issue> getIssues(@PathVariable long id) {
        return service.getReaderIssues(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        readers.deleteById(id);
    }

    @PostMapping
    public void add(@RequestBody Reader reader) {
        readers.add(reader);
    }

}