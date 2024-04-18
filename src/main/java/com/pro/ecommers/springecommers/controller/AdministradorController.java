package com.pro.ecommers.springecommers.controller;

import com.pro.ecommers.springecommers.model.Producto;
import com.pro.ecommers.springecommers.service.IProductoService;
import com.pro.ecommers.springecommers.service.IUsuarioServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IUsuarioServices usuarioServices;

    @GetMapping()
    public String home(Model model){
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos",productos);
        return "administrador/home";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model,HttpSession session){

        model.addAttribute("usuarios", usuarioServices.findAll());
        model.addAttribute("sesion",session.getAttribute("idUsuario"));
        return "administrador/usuarios";
    }
}
