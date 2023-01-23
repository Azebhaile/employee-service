package com.test.employeeservice.controller;

import com.test.employeeservice.entity.Employee;
import com.test.employeeservice.model.EmployeeModel;
import com.test.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/employee")
    public String persistEmployee(@RequestBody EmployeeModel employeeModel){
        try {
            Employee employee = new Employee();
            employee.setAddress(employeeModel.getAddress());
            employee.setId(employeeModel.getId());
            employee.setName(employeeModel.getName());
            employeeRepository.save(employee);
            return "success";
        }catch (Exception ex){
            System.out.println(ex);
            return "Failure";
        }
    }
    @DeleteMapping("/employee/{id}")
    public String removeEmployee(@PathVariable ("id") String id){
        Optional<Employee> byId = employeeRepository.findById(Long.valueOf(id));
        if(byId.isPresent()){
            employeeRepository.deleteById(Long.valueOf(id));
            return "success";
        }else{
           return "employee with id "+id+" does not exist";
        }
    }

    @PutMapping("/employee/{id}")
    public String removeEmployee(@PathVariable ("id") String id, @RequestBody EmployeeModel model){
        Optional<Employee> byId = employeeRepository.findById(Long.valueOf(id));
        if(byId.isPresent()){
           Employee employee=new Employee();
           employee.setName(model.getName());
           employee.setId(model.getId());
           employee.setAddress(model.getAddress());
           employeeRepository.save(employee);
            return "success";
        }else{
            return "employee with id "+id+" does not exist";
        }
    }
}
