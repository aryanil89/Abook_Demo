package com.it.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.it.entity.EmployeeEntity;
import com.it.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository empRepository;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
  //service method to find all employees
    public List<EmployeeEntity> getAllEmployees() throws Exception{
        return (List<EmployeeEntity>) empRepository.findAll();
    }

    public EmployeeEntity getEmployee( long empId ) {
        return empRepository.findOne(empId);
    }
    
  //service method to find an employee by Id
    public EmployeeEntity findEmployeeById( long empId ) throws Exception {
        return empRepository.findEmployeeById(empId);
    }
   
  //service method to add an employee
    public String saveEmployee( EmployeeEntity employeeEntity){
		EmployeeEntity empExist = empRepository.findEmployeeById(employeeEntity.getEmpId());
    	long empId = employeeEntity.getEmpId();
    	if(null!=empExist)
    	{
    		logger.warn("Employee with id "+empId+" is already present in database");
    		return "Employee with id "+empId+" is already present in database";
    		
    	}
		empRepository.save(employeeEntity);
		return "Employee with id "+empId+" is been created";
    		
		
    }
    
  //service method to update an employee
    public String updateEmployee( EmployeeEntity employeeEntity){
		EmployeeEntity empExist = empRepository.findEmployeeById(employeeEntity.getEmpId());
    	if(null==empExist)
    	{
    		logger.warn("Employee id does not exist");
    		String errorMessage = "Employee id does not exist";
    		return errorMessage;
    		
    	}
		empRepository.save(employeeEntity);
		return "Success";
    }
   //service method to remove an employee
    public String removeEmployee(long empId){
    	EmployeeEntity empExist = empRepository.findEmployeeById(empId);
    	if(null==empExist)
    	{	
    		logger.warn("Employee id does not exist");
    		String errorMessage = "Employee id does not exist";
    		return errorMessage;
    		
    	}
		empRepository.delete(empId);
		return "Employee with id "+empId+" has been deleted";
    }
    
}
