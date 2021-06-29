package com.springland365.hateoasdemo;

import com.springland365.hateoasdemo.department.DepartmentEntity;
import com.springland365.hateoasdemo.employee.EmployeeEntity;
import com.springland365.hateoasdemo.department.DepartmentRepo;
import com.springland365.hateoasdemo.employee.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DatabaseInitializeCommandLineRunner implements CommandLineRunner {

    @Autowired
    DepartmentRepo  departmentRepo ;

    @Autowired
    EmployeeRepo employeeRepo;
    @Override
    public void run(String... args) throws Exception {
        DepartmentEntity department ;


        department = DepartmentEntity.builder().id("D001").name("Sales").build();
        EmployeeEntity emp1 = EmployeeEntity.builder().id("EMP001").firstName("John").lastName("Doyle").build();
        EmployeeEntity emp2 = EmployeeEntity.builder().id("EMP002").firstName("Jack").lastName("Bauer").build();
        EmployeeEntity emp3 = EmployeeEntity.builder().id("EMP003").firstName("Chloe").lastName("O'Brian").build();

        department.setEmployees(List.of(emp1 , emp2 , emp3));
        departmentRepo.save(department);

        department = DepartmentEntity.builder().id("D002").name("Finance").build();
        EmployeeEntity emp4 = EmployeeEntity.builder().id("EMP004").firstName("Kim").lastName("Bauer").department(department).build();
        EmployeeEntity emp5 = EmployeeEntity.builder().id("EMP005").firstName("David").lastName("Palmer").department(department).build();
        department.setEmployees(List.of(emp4 , emp5));
        departmentRepo.save(department);

        department = DepartmentEntity.builder().id("D003").name("IT").build();
        departmentRepo.save(department);


    }
}
