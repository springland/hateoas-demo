package com.springland365.hateoasdemo.employee;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepo employeeRepo ;

    public EmployeeService(EmployeeRepo repo)
    {
        this.employeeRepo = repo ;
    }

    public Iterable<EmployeeEntity>  findAll()
    {
        return this.employeeRepo.findAll();
    }


    public Optional<EmployeeEntity> findById(String empId)
    {
        return employeeRepo.findById(empId);
    }
}
