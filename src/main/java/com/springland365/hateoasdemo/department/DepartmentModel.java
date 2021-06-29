package com.springland365.hateoasdemo.department;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.springland365.hateoasdemo.employee.EmployeeModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("department")

public class DepartmentModel extends RepresentationModel<DepartmentModel> {

    String id ;

    String name ;

    List<EmployeeModel> employees;


}
