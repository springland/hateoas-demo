package com.springland365.hateoasdemo.department;

import org.springframework.hateoas.CollectionModel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController

@RequestMapping("/departments")
public class DepartmentController {

    private DepartmentService  service ;

    private DepartmentRepresentationAssembler  assembler ;
    public DepartmentController(DepartmentService service , DepartmentRepresentationAssembler assembler)
    {
        this.service = service ;
        this.assembler = assembler ;

    }

    @GetMapping()
    public ResponseEntity<CollectionModel<DepartmentModel>> findAllDepartments()
    {
        Iterable<DepartmentEntity> iterable = this.service.findAll();

        return new ResponseEntity<CollectionModel<DepartmentModel>>(
                this.assembler.toCollectionModel(iterable),
                HttpStatus.OK
        );



    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentModel> findByDepartmentId(@PathVariable("id") String id)
    {
        return this.service.findById(id)
                .map( dept -> assembler.toModel(dept))
                .map( ResponseEntity::ok )
                .orElse(ResponseEntity.notFound().build());

    }

}
