package com.springland365.hateoasdemo.department;

import com.springland365.hateoasdemo.employee.EmployeeEntity;
import com.springland365.hateoasdemo.employee.EmployeeController;
import com.springland365.hateoasdemo.employee.EmployeeModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class DepartmentRepresentationAssembler
    extends RepresentationModelAssemblerSupport<DepartmentEntity, DepartmentModel>
{

    public DepartmentRepresentationAssembler(  )
    {
        super(DepartmentController.class , DepartmentModel.class);
    }


    @Override
    public DepartmentModel toModel(DepartmentEntity entity) {

            DepartmentModel model = DepartmentModel.builder()
                                        .id(entity.id)
                                        .name(entity.name)
                                        .build();

            Link  link = linkTo(methodOn(DepartmentController.class).findByDepartmentId(entity.getId()))
                            .withSelfRel();

            model.add( link );

            model.setEmployees(toEmployeeCollectionModel(entity.getEmployees()));
            return model ;

    }

    protected List<EmployeeModel>  toEmployeeCollectionModel(List<EmployeeEntity> employees)
    {
        return employees.stream().map(
                emp -> {
                    EmployeeModel model = EmployeeModel.builder()
                            .id(emp.getId())
                            .firstName(emp.getFirstName())
                            .lastName(emp.getLastName())
                            .build();

                    Link link = linkTo(methodOn(EmployeeController.class).findById(emp.getId())).withSelfRel();
                    model.add(link);
                    return model ;
                }


        ).collect(Collectors.toList());
    }

    @Override
    public CollectionModel<DepartmentModel>   toCollectionModel(Iterable<? extends DepartmentEntity> entities)
    {

        CollectionModel<DepartmentModel> collectionModels = super.toCollectionModel(entities);

        Link link = linkTo(methodOn(DepartmentController.class).findAllDepartments()).withSelfRel();
        collectionModels.add(link);
        return collectionModels;
    }
}
