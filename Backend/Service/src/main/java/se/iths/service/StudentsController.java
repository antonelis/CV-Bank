package se.iths.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@EnableEurekaClient
@Slf4j
@RestController
@RequestMapping("/api/v1/students")
public class StudentsController {

    @Autowired
    RestTemplate restTemplate;
    HttpClient client;

    final StudentsRepository repository;
    private final StudentsModelAssembler assembler;

    public StudentsController(StudentsRepository storage, StudentsModelAssembler studentsModelAssembler) {
        this.repository = storage;
        this.assembler = studentsModelAssembler;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping()
    public CollectionModel<EntityModel<Student>> allStudents() {
        log.info("All Students called");
        return assembler.toCollectionModel(repository.findAll());
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/{id}")
    public ResponseEntity<EntityModel<Student>> oneStudent(@PathVariable Integer id) {
        return repository.findById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping()
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        log.info("POST create Person " + student);
        var s = repository.save(student);
        log.info("Saved to repository " + s);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(linkTo(StudentsController.class).slash(s.getId()).toUri());
        return new ResponseEntity<>(assembler.toModel(s), headers, HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            log.info("User deleted");
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/{id}")
    ResponseEntity<EntityModel<Student>> replaceStudent(@RequestBody Student newStudent, @PathVariable Integer id) {
        if (repository.findById(id).isPresent()) {
            log.info("IF");
            var p = repository.findById(id)
                    .map(existingUser -> {
                        existingUser.setFirstName(newStudent.getFirstName());
                        existingUser.setLastName(newStudent.getLastName());
                        existingUser.setEducation((newStudent.getEducation()));
                        repository.save(existingUser);
                        return existingUser;
                    })
                    .get();
            var entityModel = assembler.toModel(p);
            return new ResponseEntity<>(entityModel, HttpStatus.OK);
        } else {
            log.info("ELSE!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin(origins = "http://localhost:3000")
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
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
