package se.iths.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/students")
public class StudentsController {

    final StudentsRepository repository;

    public StudentsController(StudentsRepository storage) {
        this.repository = storage;
    }

    @GetMapping()
    public List<Student> allStudents() {
        log.info("All Students called");
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> oneStudent(@PathVariable Integer id) {
        var studentOptional = repository.findById(id);
        return studentOptional.map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        log.info("POST create Person " + student);
        var s =repository.save(student);
        log.info("Saved to repository " + s);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/students/" + student.getId());
        return new ResponseEntity<>(s, headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            log.info("User deleted");
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    ResponseEntity<Student> replaceStudent(@RequestBody Student newStudent, @PathVariable Integer id) {
        return repository.findById(id).map(student -> {
            student.setFirstName(newStudent.getFirstName());
            student.setLastName(newStudent.getLastName());
            student.setEducation(newStudent.getEducation());
            repository.save(student);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/api/v1/students/" + student.getId());
            return new ResponseEntity<>(student, headers, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}")
    ResponseEntity<Student> modifyStudent(@RequestBody Student newStudent, @PathVariable Integer id) {
        return repository.findById(id).map(student -> {
            if (newStudent.getFirstName() != null)
                student.setFirstName(newStudent.getFirstName());
            if (newStudent.getLastName() != null)
                student.setLastName(newStudent.getLastName());
            if (newStudent.getEducation() != null)
                student.setEducation(newStudent.getEducation());

            repository.save(student);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/api/v1/students/" + student.getId());
            return new ResponseEntity<>(student, headers, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
