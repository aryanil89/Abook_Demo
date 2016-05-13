package com.it.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "employee_details")
public class EmployeeEntity implements Serializable {

	private static final long serialVersionUID = -392713534130528806L;

    @Id
    @NotNull
    @NumberFormat
    @Column(name = "EMP_ID")
    private long empId;
    @NotNull
    @NotEmpty
    @Column(name = "EMP_FIRST_NAME")
    private String empFName;
    @NotNull
    @NotEmpty
    @Column(name = "EMP_LAST_NAME")
    private String empLName;
    @NotNull
    @Column(name = "EMP_ADDRESS")
    private String empAddress;
    @NotNull
    @NotEmpty
    @NumberFormat
    @Column(name = "EMP_PHONE")
    private String empPhone;
    
    public String getEmpFName() {
		return empFName;
	}
	public void setEmpFName(String empFName) {
		this.empFName = empFName;
	}
	public String getEmpLName() {
		return empLName;
	}
	public void setEmpLName(String empLName) {
		this.empLName = empLName;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public String getEmpPhone() {
		return empPhone;
	}
	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}

	
	

    


}
