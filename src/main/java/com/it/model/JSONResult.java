package com.it.model;

import java.util.List;

import com.it.entity.EmployeeEntity;

public class JSONResult<T> {

	List<T> data;
	long totalCount;
    boolean success;
    private String message;

    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JSONResult() {}

    public JSONResult(List<T> data, long totalCount) {
    	this.data = data;
    	this.totalCount = totalCount;
    	this.success = true;
    }

    public JSONResult(List<T> data, long totalCount, boolean success) {
    	this.data = data;
    	this.totalCount = totalCount;
    	this.success = success;
    }

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}

