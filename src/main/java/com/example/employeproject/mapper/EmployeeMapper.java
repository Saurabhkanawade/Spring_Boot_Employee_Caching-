package com.example.employeproject.mapper;

import com.example.employeproject.entity.EmployeeEntity;
import com.example.employeproject.model.EmployeeRequest;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    public EmployeeEntity toEntity(EmployeeRequest employeeRequest);

    public EmployeeRequest toModel(EmployeeEntity employeeEntity);

    public List<EmployeeRequest> toModels(List<EmployeeEntity> employeeEntity);
}
