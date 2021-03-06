package com.it.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.it.entity.EmployeeEntity;
import com.it.model.JSONResult;
import com.it.response.ErrorResponse;
import com.it.response.SuccessResponse;
import com.it.service.EmployeeService;

@RestController
@RequestMapping("adsbook")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	//Rest controller method to return all employee details
	@RequestMapping("/employee/getAllEmployees")
	public JSONResult<EmployeeEntity> getAllEmployees() {
		JSONResult<EmployeeEntity> jsonResult = new JSONResult<EmployeeEntity>();
		//Adding all employees entity to a list and setting the status to true
		List<EmployeeEntity> list = new ArrayList<EmployeeEntity>();
		try {
			//service method call to get all employees 
			list = empService.getAllEmployees();
		} catch (Exception e) {
			//If any exception or error occurred then setting the status flag to false
			jsonResult.setSuccess(false);
			jsonResult.setMessage(e.getMessage());
			logger.error(e.getMessage());
			return jsonResult;
		}
		jsonResult.setData(list);
		jsonResult.setSuccess(true);
		jsonResult.setTotalCount(list.size());
		return jsonResult;
	}
	//Rest controller method to return  a particular employee details for an employee id passed in input
	@RequestMapping("/employee/getEmployeeById/{empId}")
	public JSONResult<EmployeeEntity> findEmployeeById(@PathVariable long empId) {
		JSONResult<EmployeeEntity> jsonResult = new JSONResult<EmployeeEntity>();
		List<EmployeeEntity> list = new ArrayList<EmployeeEntity>();
		//Adding one employee entity to a list and setting the status
		try {
			//service method call to get employee with employee id 
			list.add(empService.findEmployeeById(empId));
		} catch (Exception e) {
			//If any exception or error occurred then setting the status flag to false
			jsonResult.setSuccess(false);
			jsonResult.setMessage(e.getMessage());
			logger.error(e.getMessage());
			return jsonResult;
		}
		jsonResult.setData(list);
		jsonResult.setSuccess(true);
		jsonResult.setTotalCount(list.size());
		return jsonResult;
	}
	//Rest controller method to add a new employee
	@RequestMapping(value = "/employee/addEmployee", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveEmployee(
			@Valid @RequestBody EmployeeEntity employee,
			BindingResult bindingResults) {
		//If the input parameter body has errors
		if (bindingResults.hasErrors()) {
			// Has errors
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setCode("customCode1");
			errorResponse.setUserMessage("Please check the inputs");
			errorResponse.setInternalMessage("Please check the inputs");
			return new ResponseEntity<Object>(
					errorResponse,
					HttpStatus.BAD_REQUEST);
		} else {
			//service method call to add employee
			String message = empService.saveEmployee(employee);
			SuccessResponse successResponse = new SuccessResponse();
			successResponse.setStatus(true);
			successResponse.setMessage(message);
			return new ResponseEntity<Object>(successResponse, HttpStatus.OK);
		}
	}
	//Rest controller method to update an employee details with input parameter employee id
	@RequestMapping(value = "/employee/updateEmployee/{empId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateEmployee(
			@Valid @RequestBody EmployeeEntity employee, @PathVariable long empId,
			BindingResult bindingResults) {
		//If the input parameter body has errors
		if (bindingResults.hasErrors()) {
			// Has errors
			return new ResponseEntity<String>(
					"Bad Request, Please check your inputs",
					HttpStatus.BAD_REQUEST);
		} else {
			//service method call to update employee with employee id 
			employee.setEmpId(empId);
			String message = empService.updateEmployee(employee);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		}
	}
	//Rest controller method to remove an employee  with input parameter employee id
	@RequestMapping(value = "/employee/removeEmployee/{empId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> removeEmployee(
			@PathVariable("empId") long empId) {
		//service method call to remove employee with employee id 
		String message = empService.removeEmployee(empId);
		return new ResponseEntity<String>(message, HttpStatus.OK);

	}
    
}

