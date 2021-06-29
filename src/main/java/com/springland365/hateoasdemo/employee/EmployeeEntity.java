package com.springland365.hateoasdemo.employee;

import com.google.common.base.MoreObjects;
import com.springland365.hateoasdemo.department.DepartmentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class EmployeeEntity extends RepresentationModel<EmployeeEntity> {
    @Id
    String id ;
    String firstName ;
    String lastName ;

    @ManyToOne(fetch = FetchType.LAZY)
    DepartmentEntity department ;

    public String toString()
    {
        return MoreObjects.toStringHelper(EmployeeEntity.class)
                    .add("id" , this.id)
                    .add("firstName" , this.firstName)
                    .add("lastName" , this.lastName)
                    .add("Department" , "{ id:" + this.department.getId() + " name: " + department.getName() + " }")
                    .toString() ;
    }
}
