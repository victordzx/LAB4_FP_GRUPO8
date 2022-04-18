package com.example.lab4_grupo8.controller;
import com.example.lab4_grupo8.entity.Employees;
import com.example.lab4_grupo8.repository.DepartmentsRepository;
import com.example.lab4_grupo8.repository.EmployeesRepository;
import com.example.lab4_grupo8.repository.JobsRepository;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    JobsRepository jobsRepository;

    @Autowired
    DepartmentsRepository departmentsRepository;

    @GetMapping(value = {"","/list"})
    public String listaEmployee(Model model){
        model.addAttribute("listaEmployee", employeesRepository.findAll());
        model.addAttribute("listaJobs", jobsRepository.findAll());
        model.addAttribute("listaDepartments", departmentsRepository.findAll());
        return "employee/lista";
    }

    @GetMapping("/new")
    public String nuevoEmployeeForm(@ModelAttribute("employees")  Employees employees, Model model) {
        model.addAttribute("listaJobs", jobsRepository.findAll());
        model.addAttribute("listaJefes", employeesRepository.findAll());
        model.addAttribute("listaDepartments", departmentsRepository.findAll());
        return "employee/form";
    }

    @PostMapping("/save")
    public String guardarEmployee(@ModelAttribute("employees") @Valid Employees employees, BindingResult bindingResult,
                                  RedirectAttributes attr,
                                  @RequestParam(name="fechaContrato", required=false) String fechaContrato, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listaJobs", jobsRepository.findAll());
            model.addAttribute("listaJefes", employeesRepository.findAll());
            model.addAttribute("listaDepartments", departmentsRepository.findAll());
            return "employee/form";
        } else {

            if (employees.getEmployeeid() == 0) {
                attr.addFlashAttribute("msg", "Empleado creado exitosamente");
                employees.setHiredate(LocalDateTime.now());
                employeesRepository.save(employees);
                return "redirect:/employee/list";
            } else {
                Optional<Employees> employeesOptional = employeesRepository.findById(employees.getEmployeeid());

                if(employeesOptional.isPresent()){
                    Employees employeesVal = employeesOptional.get();

                    if(employeesVal.getJobs().getJobid().equals(employees.getJobs().getJobid())){
                        employees.setHiredate(employees.getHiredate());
                    }else{
                        employees.setHiredate(LocalDateTime.now());
                    }

                }
                try{
                    employeesRepository.save(employees);
                }catch (Exception e){
                    model.addAttribute("msgSal","No puede ingreser un salario mayor a 8 decimales");
                    return "/employee/form";
                }

                attr.addFlashAttribute("msg", "Empleado actualizado exitosamente");
                return "redirect:/employee/list";
            }
        }
    }



    @GetMapping("/edit")
    public String editarEmployee(@ModelAttribute("employees") Employees employees,
                                 @RequestParam("id") int id,
                                 Model model) {
        Optional<Employees> employeesOptional = employeesRepository.findById(id);
        if (employeesOptional.isPresent()) {
            employees = employeesOptional.get();
            model.addAttribute("employees", employees);
            model.addAttribute("listaJobs", jobsRepository.findAll());
            model.addAttribute("listaJefes", employeesRepository.findAll());
            model.addAttribute("listaDepartments", departmentsRepository.findAll());
            return "/employee/form";
        } else {
            return "redirect:/employee/list";
        }
    }



    @GetMapping("/delete")
    public String borrarEmpleado(Model model,
                                 @RequestParam("id") int id,
                                 RedirectAttributes attr) {

        Optional<Employees> optEmployees = employeesRepository.findById(id);

        if (optEmployees.isPresent()) {
            employeesRepository.deleteById(id);
            attr.addFlashAttribute("msg","Empleado borrado exitosamente");
        }
        return "redirect:/employee/list";
    }

  /*  @PostMapping("/search")
    public String buscar (){

        //COMPLETAR
    }*/

}
