package com.example.LAB4_FP_GRUPO8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/history")
public class HistoryController {

    @GetMapping(value = {"","/"})
    public String historialEmpleado(){
        //COMPLETAR
    }



}
