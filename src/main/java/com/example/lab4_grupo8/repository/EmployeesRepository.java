package com.example.lab4_grupo8.repository;

import com.example.lab4_grupo8.entity.Departments;
import com.example.lab4_grupo8.entity.Employees;
import com.example.lab4_grupo8.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {

    List<Employees> findEmployeesByFirstname(String firstname);
    List<Employees> findEmployeesByLastname(String lastname);
    List<Employees> findEmployeesByJobs(Jobs jobs);
    List<Employees> findEmployeesByDepartments(Departments departments);

}
