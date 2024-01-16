package SpringLearning.SpringDemo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentsController {
    private final Students students;

    public StudentsController(Students students) {
        this.students = students;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> getStudents() {
        return students.getAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable("id") long userId) {
        return students.getById(userId);
    }

    @GetMapping("/search")
    public List<Student> getByName(@RequestParam String name) {
        return students.getByName(name);
    }

    @GetMapping("/group/{groupName}")
    public List<Student> getByGroup(@PathVariable("groupName") String group) {
        return students.getByGroup(group);
    }

    @PostMapping()
    public void add(@RequestBody Student student) {
        students.addStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        students.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void updateStudent(@RequestBody Student student, @PathVariable("id") long id) {
        Student existStudent = students.getById(id);
        if (existStudent != null) {
            existStudent.setFullName(student.getFullName());
            existStudent.setGroup(student.getGroup());
        } else {
            throw new IllegalArgumentException();
        }
    }
}
