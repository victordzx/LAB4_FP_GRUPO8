package com.example.LAB4_FP_GRUPO8.repository;

import com.example.LAB4_FP_GRUPO8.entity.Departments;
import com.example.LAB4_FP_GRUPO8.entity.Employees;
import com.example.LAB4_FP_GRUPO8.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {

    List<Employees> findEmployeesByFirstname(String firstname);
    List<Employees> findEmployeesByLastname(String lastname);
    List<Employees> findEmployeesByJobs(Jobs jobs);
    List<Employees> findEmployeesByDepartments(Departments departments);

}
