package com.springland365.hateoasdemo.employee;

import com.springland365.hateoasdemo.department.DepartmentController;
import com.springland365.hateoasdemo.department.DepartmentEntity;
import com.springland365.hateoasdemo.department.DepartmentModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeRepresentationAssembler  extends RepresentationModelAssemblerSupport<EmployeeEntity, EmployeeModel> {

    public EmployeeRepresentationAssembler()
    {
        super(EmployeeController.class , EmployeeModel.class);
    }
    @Override
    public EmployeeModel toModel(EmployeeEntity entity) {

        EmployeeModel model ;

        model = EmployeeModel.builder()
                    .lastName(entity.getLastName())
                    .firstName(entity.getFirstName())
                    .id(entity.getId())
                    .build();

        Link link =  linkTo(methodOn(EmployeeController.class).findById(entity.getId()) ).withSelfRel();

        model.add(link);
        model.setDepartment(toDepartmentModel(entity));
        return model;
    }

    protected DepartmentModel toDepartmentModel(EmployeeEntity entity)
    {
        Optional<DepartmentEntity> deptEntity = Optional.ofNullable(entity.getDepartment());

        DepartmentModel deptModel = deptEntity.map(
                d -> {
                    DepartmentModel model = DepartmentModel.builder()
                            .name(d.getName())
                            .id(d.getId())
                            .build();

                    Link link = linkTo(methodOn(DepartmentController.class).findByDepartmentId(d.getId())).withSelfRel();
                    model.add(link);
                    return model ;
                }

        ).orElse(null);
        return deptModel;
    }

    @Override
    public CollectionModel<EmployeeModel> toCollectionModel(Iterable<? extends EmployeeEntity> iterator)
    {
        CollectionModel<EmployeeModel> collectionModel =super.toCollectionModel(iterator);
        collectionModel.add(getEmployeesLink());
        return collectionModel ;
    }
    public Link getEmployeesLink()
    {

        Link link = linkTo(methodOn(EmployeeController.class).findAll()).withRel("employees");

        return link ;
    }
}
