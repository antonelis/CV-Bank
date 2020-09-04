package se.iths.service;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudentsModelAssembler implements RepresentationModelAssembler<Student, EntityModel<Student>> {
    @Override
    public EntityModel<Student> toModel(Student student) {
        return new EntityModel<>(student,
                linkTo(methodOn(StudentsController.class).oneStudent(student.getId())).withSelfRel(),
                linkTo(methodOn(StudentsController.class).allStudents()).withRel("Student"));
    }

    @Override
    public CollectionModel<EntityModel<Student>> toCollectionModel(Iterable<? extends Student> entities) {
        var collection = StreamSupport.stream(entities.spliterator(), false)
                .map(this::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(collection,
                linkTo(methodOn(StudentsController.class).allStudents()).withSelfRel());
    }
}
