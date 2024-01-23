package Library.repository;

import Library.model.Book;
import Library.model.Reader;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ReaderRepository {
    private List<Reader> readers;

    public ReaderRepository() {
        readers = new ArrayList<>();
    }

    @PostConstruct
    public void generateReaders() {
        readers.addAll(List.of(
                new Reader("Игорь"),
                new Reader("Коля")));
    }

    public List<Reader> getAll() {
        return readers;
    }

    public Reader getById(long id) {
        return readers.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public void deleteById(long id) {
        readers.removeIf(it -> Objects.equals(it.getId(), id));
    }

    public void add(Reader reader) {
        readers.add(reader);
    }
}