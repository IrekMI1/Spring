package Library.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Book {
    private static long count = 1L;
    private long id;
    private String name;
    private String description;

    public Book(String name, String description) {
        this.id = count++;
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
