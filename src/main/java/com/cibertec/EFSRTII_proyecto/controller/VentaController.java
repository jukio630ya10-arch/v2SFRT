package com.cibertec.EFSRTII_proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.EFSRTII_proyecto.service.MovimientoInventarioService;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private MovimientoInventarioService movimientoInventarioService;

    @GetMapping
    public String listarVentas(Model model) {
        model.addAttribute("lista", movimientoInventarioService.listarSalidas());
        return "ventas/lista";
    }
}
