package com.example.employeproject.service;

import com.example.employeproject.entity.EmployeeEntity;
import com.example.employeproject.mapper.EmployeeMapper;
import com.example.employeproject.model.EmployeeRequest;
import com.example.employeproject.model.EmployeeResponse;
import com.example.employeproject.repository.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class EmployeeService {


    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public List<EmployeeRequest> getEmployee() {
        log.info("fetched all the employees from the database..............");
        List<EmployeeRequest> employeeRequest = employeeMapper.toModels(employeeRepository.findAll());
        return employeeRequest;
    }

    @Cacheable(cacheNames = "employee-get-cache" , key = "#employeId")
    public EmployeeRequest getEmployeeById(Long employeId) {
        EmployeeRequest employeeRequest = new EmployeeRequest();
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeId);
        if (employeeEntity.isPresent()) {
            log.info("Employee is present."+employeId+"..and get from the database..............");
            employeeRequest = employeeMapper.toModel(employeeEntity.get());
        } else {
            log.info("Employe is not found with id" + employeId);
        }
        return employeeRequest;
    }

    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        EmployeeEntity employeeEntity = null;
        employeeEntity = employeeMapper.toEntity(employeeRequest);
        EmployeeResponse employeeResponse = new EmployeeResponse();
        Long ranId = (long) (Math.random() * 20);
        log.info(ranId);
        employeeEntity.setEmployeeId((long) ranId);
        employeeResponse.setEmployeeId(employeeEntity.getEmployeeId());
        employeeRepository.save(employeeEntity);
        log.info("Employee is created into the database with id=" + employeeEntity.getEmployeeId());
        return employeeResponse;
    }

    @CachePut(cacheNames = "employee-update-cache" , key = "#employeeId")
    public void updateEmployee(Long employeeId, EmployeeRequest employeeRequest) {
        Optional<EmployeeEntity> employeeEntity =employeeRepository.findById(employeeId);
        if(employeeEntity.isPresent()){
//            employeeMapper.toEntity(employeeRequest);
            employeeEntity.get().setEmployeeId(employeeId);
            employeeEntity.get().setFirstName(employeeRequest.getFirstName());
            employeeEntity.get().setLastName(employeeRequest.getLastName());
            employeeRepository.save(employeeEntity.get());
        }
        log.info("Updated the data from the database....................");
    }

    @CacheEvict(cacheNames = "employee-delete-cache",key = "#employeeId")
    public void deleteEmployee(Long employeeId) {
        Optional<EmployeeEntity> personEntityOptional = employeeRepository.findById(employeeId);
        if (personEntityOptional.isPresent()) {
            employeeRepository.deleteById(employeeId);
        } else {
            log.info("Person id " + employeeId + "not found");
        }
        log.info("deleted success....................");
    }
}


