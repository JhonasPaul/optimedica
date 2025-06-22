package com.optimedica.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StatausController {

    @GetMapping("/status")
    public String prueba() {
        return "conexion exitosa";
    }
}
