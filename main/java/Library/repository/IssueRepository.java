package Library.repository;

import Library.model.Issue;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class IssueRepository {
    private final List<Issue> issueList;

    public IssueRepository() {
        issueList = new ArrayList<>();
    }

    public List<Issue> getAll() {
        return issueList;
    }

    public Issue getById(long id) {
        return issueList.stream()
                .filter(it -> (Objects.equals(it.getId(), id)))
                .findFirst()
                .orElse(null);
    }

    public void save(Issue issue) {
        issueList.add(issue);
    }
}