package Library.repository;

import Library.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Integer> {

    public Optional<Reader> findById(Integer id);

    public void deleteById(Integer id);
}