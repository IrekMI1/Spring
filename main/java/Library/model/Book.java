package Library.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    public Book(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @JsonCreator
    public Book(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}