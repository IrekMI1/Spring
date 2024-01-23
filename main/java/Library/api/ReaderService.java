package Library.api;

import Library.model.Issue;
import Library.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReaderService {
    @Autowired
    private IssueRepository issueRepository;

    public List<Issue> getReaderIssues(long readerId) {
        return issueRepository.getAll().stream()
                .filter(it -> (Objects.equals(it.getReaderId(), readerId) && it.getReturned_at() == null))
                .collect(Collectors.toList());
    }
}