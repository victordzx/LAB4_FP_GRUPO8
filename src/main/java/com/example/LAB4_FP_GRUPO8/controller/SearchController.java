package com.example.LAB4_FP_GRUPO8.controller;


import com.example.LAB4_FP_GRUPO8.repository.DepartmentsRepository;
import com.example.LAB4_FP_GRUPO8.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/Search")
public class SearchController {

    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    DepartmentsRepository departmentsRepository;


    @GetMapping(value = {"","/"})
    public String indice(){
        return "Search/indice";
    }

    @GetMapping(value = {"/Salario"})
    public String listaEmpleadosMayorSalrio (Model model, @RequestParam(value = "searchField", defaultValue = "") String searchField,
                                             RedirectAttributes attributes    ){

        if(searchField.isEmpty()){
            model.addAttribute("employeesList", employeesRepository.listaEmpleadosMaxSalario());
            model.addAttribute("msg", null);
        } else{
            try{

                Double salary = Double.valueOf(searchField);
                //  employeesList = employeesRepository.findAllBySalaryEqualsAndSalaryGreaterThan(salary, BigDecimal.valueOf(8000));
                model.addAttribute("msg", null);
                model.addAttribute("employeesList", employeesRepository.listaEmpleadosMaxSalarioEncs(salary));

            }catch (NumberFormatException e){
                // employeesList = employeesRepository.findAllBySalaryGreaterThan(BigDecimal.valueOf(8000));
                model.addAttribute("msg", "El valor ingresado debe ser un numero");
                model.addAttribute("employeesList", employeesRepository.listaEmpleadosMaxSalario());

            }
        }
        //COMPLETAR
        return "Search/lista2";
    }

    @PostMapping("/busqueda")

    public String buscar (
                @RequestParam(value = "searchField", defaultValue = "") String searchField,
                Model model,
                RedirectAttributes redirectAttributes
    ){

            redirectAttributes.addAttribute("searchField", searchField);
            return "redirect:/Search/Salario";
            //COMPLETAR
        }



    @GetMapping(value = "/Filtro2")
    public String cantidadEmpleadosPorPais (Model model){

        model.addAttribute("reporteSalarioPromedio",  departmentsRepository.reporteSalarioPromedio());
        return "/Search/salario";
    }

    @GetMapping("/listar")
    public String listarEmpleadoDep(@RequestParam("id") int id, Model model) {
        model.addAttribute("listaEmpleadosXDep", employeesRepository.reporteEmpleadosPorDepartamentos(id));
        return "/Search/lista3";

    }


}
