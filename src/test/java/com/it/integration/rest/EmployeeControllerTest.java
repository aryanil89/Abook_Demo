package com.it.integration.rest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.*;
import com.it.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.it.controller.EmployeeController;
import com.it.entity.EmployeeEntity;
import com.it.model.JSONResult;
import com.it.repository.EmployeeRepository;

@RunWith(SpringJUnit4ClassRunner.class)//It is a custom extension of JUnit’s BlockJUnit4ClassRunner which provides functionality of the Spring TestContext Framework.
@SpringApplicationConfiguration(classes = Application.class)//It is a Class-level annotation that is used to determine how to load and configure an ApplicationContext for integration tests. We provide it with the configuration class which in this case is Application class.
@WebIntegrationTest//Test class annotation signifying that the tests are “web integration tests” and therefore require full startup in the same way as a production application.
public class EmployeeControllerTest {
	//Required to Generate JSON content from Java objects
	  public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	  //Required to delete the data added for tests.
	  //Directly invoke the APIs interacting with the DB
	  @Autowired
	  private EmployeeRepository employeeRepository;

	  //Test RestTemplate to invoke the APIs.
	  private RestTemplate restTemplate = new TestRestTemplate();
	  
	  @Test
	  public void testSaveEmployeeApi() throws JsonProcessingException{
		//Building the Request body data
		  Map<String, Object> requestBody = new HashMap<String, Object>();
		  requestBody.put("empId", "478738");
		  requestBody.put("empFName", "Indranil");
		  requestBody.put("empLName", "Acharya");
		  requestBody.put("empAddress", "10631CA");
		  requestBody.put("empPhone", "8582753470");
		  HttpHeaders requestHeaders = new HttpHeaders();
		  requestHeaders.setContentType(MediaType.APPLICATION_JSON);

		  //Creating http entity object with request body and headers
		  HttpEntity<String> httpEntity =
		      new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);

		  //Invoking the API
		  Map<String, Object> apiResponse =
		      restTemplate.postForObject("http://localhost:8085/adsbook/employee/addEmployee", httpEntity, Map.class, Collections.EMPTY_MAP);
		  assertNotNull(apiResponse);

		  //Asserting the response of the API.
		  String message = apiResponse.get("message").toString();
		  System.out.println("No errors");

		  
		  assertEquals("Employee with id 478738 is been created", message);

		  //Fetching the Employee details directly from the DB to verify the API succeeded
		  EmployeeEntity employeeEntity = employeeRepository.findOne(478738L);
		  assertEquals("Indranil", employeeEntity.getEmpFName());
		  assertEquals("Acharya", employeeEntity.getEmpLName());
		  assertEquals("10631CA", employeeEntity.getEmpAddress());
		  assertEquals("8582753470", employeeEntity.getEmpPhone());

		  //Delete the data added for testing
		  employeeRepository.delete(478738L);
		  
	  }
	  
	  @Test
	  public void testEmployeeEmployeeWithIdApi() throws IOException{
		  //Building the Request body to insert data into database directly
		  EmployeeEntity employeeEntity = new EmployeeEntity();
		  employeeEntity.setEmpId(478738L);
		  employeeEntity.setEmpFName("Indranil");
		  employeeEntity.setEmpLName("Acharya");
		  employeeEntity.setEmpAddress("10631CA");
		  employeeEntity.setEmpPhone("8582753470");
		  employeeRepository.save(employeeEntity);
		  HttpHeaders requestHeaders = new HttpHeaders();
		  requestHeaders.setContentType(MediaType.APPLICATION_JSON);

		  //Creating http entity object with request body and headers
		  HttpEntity<String> httpEntity =
		      new HttpEntity<String>(requestHeaders);
		  //Invoking the API
		  String apiResponse = restTemplate.getForObject("http://localhost:8085/adsbook/employee/getEmployeeById/478738",String.class);
		  JSONResult<EmployeeEntity> jsonResult = ( JSONResult<EmployeeEntity>) returnJsonResponseAsObject(apiResponse,
		          new TypeReference<JSONResult<EmployeeEntity>>()
		          {
		          });
		  assertNotNull(apiResponse);
		  
		  EmployeeEntity responseEntity = jsonResult.getData().get(0);
		  System.out.println(responseEntity.getEmpId());
		  assertEquals(478738L, responseEntity.getEmpId());
		  assertEquals("Indranil", responseEntity.getEmpFName());
		  assertEquals("Acharya", responseEntity.getEmpLName());
		  assertEquals("10631CA", responseEntity.getEmpAddress());
		  assertEquals("8582753470", responseEntity.getEmpPhone());
		  employeeRepository.delete(478738L);
		  
		  
	  }
	  public Object returnJsonResponseAsObject(String response, TypeReference typeReference) throws IOException
	  {

	    ObjectMapper objectMapper = new ObjectMapper();
	    Object responseAsObject = objectMapper.readValue(response, typeReference);
	    return responseAsObject;
	  }
	  
	  
	  

}