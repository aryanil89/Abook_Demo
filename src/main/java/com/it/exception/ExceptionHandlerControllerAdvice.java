package com.it.exception;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.it.controller.EmployeeController;

@ControllerAdvice
//This class will handle all Runtime exception as root class has been mentioned Exception.class
public class ExceptionHandlerControllerAdvice {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<Error>> handleValidationException( Exception ex ) {
    	logger.error(ex.getLocalizedMessage());
        return new ResponseEntity<List<Error>>(Arrays.asList(new Error(ex.getMessage())),
                HttpStatus.BAD_REQUEST);
    }

}
