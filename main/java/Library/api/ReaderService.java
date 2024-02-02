package Library.api;

import Library.model.Issue;
import Library.model.Reader;
import Library.repository.IssueRepository;
import Library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReaderService {
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private IssueRepository issueRepository;

    public List<Reader> getAll() {
        List<Reader> readers = readerRepository.findAll();
        if (readers.isEmpty()) {
            throw new NoSuchElementException();
        }
        return readers;
    }

    public Reader getById(Integer id) {
        Optional<Reader> reader = readerRepository.findById(id);
        if (reader.isEmpty()) {
            throw new NoSuchElementException();
        }
        return reader.get();
    }

    public List<Issue> getReaderIssues(Integer readerId) {
        List<Issue> issues = issueRepository.findAll().stream()
                .filter(it -> (Objects.equals(it.getReaderId(), readerId) && it.getReturned_at() == null))
                .toList();
        if (issues.isEmpty()) {
            throw new NoSuchElementException();
        }
        return issues;
    }

    public void deleteById(Integer id) {
        if (readerRepository.existsById(id)) {
            readerRepository.deleteById(id);
        } else {
            throw new NoSuchElementException();
        }
    }

    public Reader add(Reader reader) {
        return readerRepository.save(reader);
    }
}