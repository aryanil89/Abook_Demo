package com.it.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.it.entity.EmployeeEntity;
import com.it.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository empRepository;

    public List<EmployeeEntity> getAllEmployees() throws Exception{
        return (List<EmployeeEntity>) empRepository.findAll();
    }

    public EmployeeEntity getEmployee( long empId ) {
        return empRepository.findOne(empId);
    }

    public EmployeeEntity findEmployeeById( long empId ) throws Exception {
        return empRepository.findEmployeeById(empId);
    }
   
    public String saveEmployee( EmployeeEntity employeeEntity){
		EmployeeEntity empExist = empRepository.findEmployeeById(employeeEntity.getEmpId());
    	long empId = employeeEntity.getEmpId();
    	if(null!=empExist)
    	{
    		return "Employee with id "+empId+" is already present in database";
    		
    	}
		empRepository.save(employeeEntity);
		return "Employee with id "+empId+" is been created";
    		
		
    }
    
    public String updateEmployee( EmployeeEntity employeeEntity){
		EmployeeEntity empExist = empRepository.findEmployeeById(employeeEntity.getEmpId());
    	if(null==empExist)
    	{
    		System.out.println("Employee id does not exist");
    		String errorMessage = "Employee id does not exist";
    		return errorMessage;
    		
    	}
		empRepository.save(employeeEntity);
		return "Success";
    }
    public String removeEmployee(long empId){
    	EmployeeEntity empExist = empRepository.findEmployeeById(empId);
    	if(null==empExist)
    	{
    		System.out.println("Employee id does not exist");
    		String errorMessage = "Employee id does not exist";
    		return errorMessage;
    		
    	}
		empRepository.delete(empId);
		return "Employee with id "+empId+" has been deleted";
    }
    
    
    
/*    public String updateEmployee(  long empId,  String fname,  String lname,  String address, String phone ){
    	EmployeeEntity empExist = empRepository.findEmployeeById(empId);
    	
    	if(empExist==null)
    	{
    		System.out.println("Employee id does not exist");
    		String errorMessage = "Employee id does not exist";
    		return errorMessage;
    	}
    	empExist.setEmpFName(fname);
    	empExist.setEmpLName(lname);
    	empExist.setEmpAddress(address);
    	empExist.setEmpPhone(phone);
		empRepository.save(empExist);
		return "Success";
    }
    public String removeEmployee(long empId){
		EmployeeEntity empEntity = new EmployeeEntity();
		empRepository.delete(empId);
		return "Employee with id "+empId+" has been deleted";
    }
    
    public void saveData(){
    	EmployeeEntity empEntity = new EmployeeEntity();
       	empEntity.setEmpId(1L);
    	empEntity.setEmpFName("Indranil");
    	empEntity.setEmpLName("Acharya");
    	empEntity.setEmpAddress("10631 Caminito Alvarez");
    	empEntity.setEmpPhone("8582753470");
    	empRepository.save(empEntity);
    }
    
    public String saveEmployee(  long empId,  String fname,  String lname,  String address, String phone ){
		EmployeeEntity empExist = empRepository.findEmployeeById(empId);
    	
    	if(null!=empExist)
    	{
    		return "Employee with id "+empId+" is already present in database";
    		
    	}
    	empExist = new EmployeeEntity();
    	empExist.setEmpId(empId);
		empExist.setEmpFName(fname);
		empExist.setEmpLName(lname);
		empExist.setEmpAddress(address);
		empExist.setEmpPhone(phone);
		empRepository.save(empExist);
		return "Success";
    		
		
    }*/
}
