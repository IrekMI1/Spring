package Library.repository;

import Library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    public Optional<Book> findById(Integer id);

    public void deleteById(Integer id);
}