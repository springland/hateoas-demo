package com.springland365.hateoasdemo.employee;

import com.springland365.hateoasdemo.department.DepartmentModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel extends RepresentationModel<EmployeeModel> {

    String id ;
    String firstName ;
    String lastName ;

    DepartmentModel department ;

}
