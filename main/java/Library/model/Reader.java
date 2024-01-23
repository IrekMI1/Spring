package Library.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Reader {
    private static long count = 1L;
    private long id;
    private String name;

    public Reader(String name) {
        id = count++;
        this.name = name;
    }

    @JsonCreator
    public Reader(long id, String name) {
        if (id < count) {
            this.id = count++;
        } else {
            this.id = id;
        }
        this.name = name;
    }
}
