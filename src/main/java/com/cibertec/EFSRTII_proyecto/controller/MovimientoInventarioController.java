package com.cibertec.EFSRTII_proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.EFSRTII_proyecto.model.MovimientoInventario;
import com.cibertec.EFSRTII_proyecto.service.MovimientoInventarioService;
import com.cibertec.EFSRTII_proyecto.service.ProductoService;
import com.cibertec.EFSRTII_proyecto.service.UsuarioService;

@Controller
@RequestMapping("/movimientos")
public class MovimientoInventarioController {

    @Autowired
    private MovimientoInventarioService service;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", service.listar());
        return "movimiento/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("movimiento", new MovimientoInventario());
        model.addAttribute("productos", productoService.listar());
        model.addAttribute("usuarios", usuarioService.listar());
        return "movimiento/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute MovimientoInventario movimiento) {
        service.guardar(movimiento);
        return "redirect:/movimientos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/movimientos";
    }
}
