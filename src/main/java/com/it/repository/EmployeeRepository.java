package com.it.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.it.entity.EmployeeEntity;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {

    @Query("Select e from EmployeeEntity e where e.empId= :empId")
    public EmployeeEntity findEmployeeById( @Param("empId" ) long empId);
    /*@Query("Select e from EmployeeEntity e where e.empId= :empId")
    public EmployeeEntity findEmployeeById( @Param("empId" ) String empId);*/

}
