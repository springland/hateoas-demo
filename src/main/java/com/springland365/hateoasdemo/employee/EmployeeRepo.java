package com.springland365.hateoasdemo.employee;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepo extends CrudRepository<EmployeeEntity, String> {
}
