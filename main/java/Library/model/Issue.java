package Library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "issues")
@RequiredArgsConstructor
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Integer id;
    @Column(name = "bookId")
    private Integer bookId = null;
    @Column(name = "readerId")
    private Integer readerId = null;
    @Column(name = "issued_at")
    private final LocalDateTime issued_at;
    @Column(name = "returned_at")
    private LocalDateTime returned_at;

    public Issue() {
        id = null;
        issued_at = LocalDateTime.now();
        returned_at = null;
    }

    public Issue(Integer bookId, Integer readerId) {
        id = null;
        this.readerId = readerId;
        this.bookId = bookId;
        issued_at = LocalDateTime.now();
        returned_at = null;
    }
}