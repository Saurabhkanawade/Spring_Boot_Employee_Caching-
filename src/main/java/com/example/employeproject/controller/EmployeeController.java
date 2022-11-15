package com.example.employeproject.controller;

import com.example.employeproject.entity.EmployeeEntity;
import com.example.employeproject.model.EmployeeRequest;
import com.example.employeproject.model.EmployeeResponse;
import com.example.employeproject.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@Log4j2

public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(path = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeRequest>> getEmployee() {
        List<EmployeeRequest> employeeRequests = employeeService.getEmployee();
        log.info("fetching the data from the database");
        return new ResponseEntity<>(employeeRequests, HttpStatus.OK);
    }

    @GetMapping(path = "/employees/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeRequest> getEmployeeById(@PathVariable Long employeeId) {
        EmployeeRequest employeeRequest = employeeService.getEmployeeById(employeeId);
        if (Objects.isNull(employeeRequest)) {
            log.info("Person id " + employeeId + "Is not Found");
        } else {
            ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(employeeRequest, HttpStatus.OK);
    }

    @PostMapping(path = "/employees", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        log.info("adding the  employees..........");
        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }

    @PutMapping(path = "employees/{employeeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateEmployee(@PathVariable Long employeeId,
                                               @RequestBody EmployeeRequest employeeRequest) {
        log.info("updating the employee................");

        employeeService.updateEmployee(employeeId, employeeRequest);

//        if (!Objects.isNull(employeeRequest)) {
//            employeeService.updateEmployee(employeeId, employeeRequest);
//        } else {
//            log.info("The employeeId "+employeeId+" is not found..");
//            return ResponseEntity.noContent().build();
//        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/employees/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteEmployee(@PathVariable Long employeeId) {
        log.info("Deleting the employee..........");
        employeeService.deleteEmployee(employeeId);
    }
}

























