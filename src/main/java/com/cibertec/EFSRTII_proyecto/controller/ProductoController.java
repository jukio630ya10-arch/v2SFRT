package com.cibertec.EFSRTII_proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cibertec.EFSRTII_proyecto.model.Categoria;
import com.cibertec.EFSRTII_proyecto.model.Producto;
import com.cibertec.EFSRTII_proyecto.service.CategoriaService;
import com.cibertec.EFSRTII_proyecto.service.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;
    
    @Autowired
    private CategoriaService categoriaService;


    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", service.listar());
        return "productos/lista";
    }
    
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
    	 Producto producto = new Producto();
         producto.setCategoria(new Categoria());
         model.addAttribute("producto", producto);
         model.addAttribute("categorias", categoriaService.listar());
        return "productos/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto, @RequestParam("categoria.id") Long categoriaId) {
        producto.setCategoria(categoriaService.obtenerPorId(categoriaId));
        service.guardar(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("producto", service.obtenerPorId(id));
        model.addAttribute("categorias", categoriaService.listar());
        return "productos/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/productos";
    }

}
