package SpringLearning.SpringDemo;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class Students {
    private List<Student> students = new ArrayList<>();

    public Students() {
        students.addAll(List.of(
                new Student("Иван Матвеев", "4131-42"),
                new Student("Кирилл Хамов", "4131-43"),
                new Student("Мария Долина", "4131-41"),
                new Student("Евгений Яковлев", "4131-44"),
                new Student("Анна Басова", "4131-42"),
                new Student("Николай Дутев", "4131-43"),
                new Student("Фома Понтьев", "4131-44")
        ));
    }

    public Student getById(long userId) {
        return students.stream()
                .filter(it -> it.getId() == userId)
                .findFirst()
                .orElse(null);
    }

    public List<Student> getAll() {
        return List.copyOf(students);

    }

    public List<Student> getByName(String name) {
        return students.stream()
                .filter(it -> it.getFullName().contains(name))
                .collect(Collectors.toList());
    }

    public List<Student> getByGroup(String group) {
        return students.stream()
                .filter(it -> it.getGroup().equals(group))
                .collect(Collectors.toList());
    }

    public void deleteById(long id) {
        students.removeIf(it -> Objects.equals(it.getId(), id));
    }

    public void addStudent(Student student) {
        students.add(student);
    }
}
