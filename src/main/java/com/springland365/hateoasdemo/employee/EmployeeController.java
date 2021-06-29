package com.springland365.hateoasdemo.employee;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "employees" , produces="application/hal+json")
public class EmployeeController {

    private EmployeeRepresentationAssembler assembler;

    private EmployeeService employeeService;

    public  EmployeeController(EmployeeService service ,
                               EmployeeRepresentationAssembler assembler)
    {
        this.employeeService = service ;
        this.assembler = assembler;
    }

    @GetMapping()
    public Iterable<EmployeeModel> findAll()
    {
        return assembler.toCollectionModel(this.employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeModel> findById(@PathVariable("id") String id)
    {
         return this.employeeService.findById(id).map( emp -> assembler.toModel(emp))
                 .map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
    }


}
