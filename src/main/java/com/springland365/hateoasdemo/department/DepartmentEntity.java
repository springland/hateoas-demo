package com.springland365.hateoasdemo.department;

import com.google.common.base.MoreObjects;
import com.springland365.hateoasdemo.employee.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class DepartmentEntity extends RepresentationModel<DepartmentEntity> {

    @Id
    String id ;

    String name ;

    @OneToMany(cascade = CascadeType.ALL )
    List<EmployeeEntity> employees;

    @Override
    public String toString()
    {
        String employeesStr = employees.stream().map( emp -> "{ id: "+ emp.getId() + " ,firstName :" + emp.getFirstName() + ", lastName :" + emp.getLastName() + " }")
                .collect(Collectors.joining());
        return MoreObjects.toStringHelper(DepartmentEntity.class)
                    .add("Id" , this.id)
                    .add("Name" , this.name)
                    .add("employees" , employeesStr).toString();
    }
}
