package com.springland365.hateoasdemo.department;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    DepartmentRepo departmentRepo ;

    public DepartmentService(DepartmentRepo repo)
    {
        this.departmentRepo = repo;
    }

    public Iterable<DepartmentEntity> findAll()
    {
        return this.departmentRepo.findAll();
    }

    public Optional<DepartmentEntity> findById(String deptId)
    {
        return this.departmentRepo.findById(deptId);
    }
}
