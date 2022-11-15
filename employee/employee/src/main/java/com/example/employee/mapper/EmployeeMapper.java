package com.example.employee.mapper;

import com.example.employee.entity.EmployeeEntity;
import com.example.employee.model.EmployeeRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface EmployeeMapper {

    EmployeeEntity modelToEnitity(EmployeeRequest employeeRequest);
    EmployeeRequest enitityToModel(EmployeeEntity employeeEntity);

}
