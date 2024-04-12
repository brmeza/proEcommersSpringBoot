package com.pro.ecommers.springecommers.controller;

import com.pro.ecommers.springecommers.model.Producto;
import com.pro.ecommers.springecommers.model.Usuario;
import com.pro.ecommers.springecommers.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.UncategorizedScriptException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;

    @GetMapping("")
    public String show(Model model){
        model.addAttribute("productos", productoService.findAll());
        return "productos/show";
    }

    @GetMapping("/create")
    public String create(){
        return "productos/create";
    }

    @PostMapping("/save")
    public String Save(Producto producto){
        LOGGER.info("Este es el objeto producto {}",producto);
        Usuario u = new Usuario(1,"","","","","","","");
        producto.setUsuario(u);

        productoService.Save(producto);
        return "redirect:/productos";
    }


}
