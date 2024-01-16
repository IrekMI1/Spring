package SpringLearning.SpringDemo;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data
public class Student {
    private static long counter = 1L;
    private final long id;
    private String fullName;
    private String group;

    @JsonCreator
    public Student(long id, String fullName, String group) {
        this.id = id;
        this.fullName = fullName;
        this.group = group;
    }

    public Student(String fullName, String group) {
        this.id = counter++;
        this.fullName = fullName;
        this.group = group;
    }
}
