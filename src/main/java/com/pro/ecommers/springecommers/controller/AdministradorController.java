package com.pro.ecommers.springecommers.controller;

import com.pro.ecommers.springecommers.model.Orden;
import com.pro.ecommers.springecommers.model.Producto;
import com.pro.ecommers.springecommers.service.IOrdenService;
import com.pro.ecommers.springecommers.service.IProductoService;
import com.pro.ecommers.springecommers.service.IUsuarioServices;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IUsuarioServices usuarioServices;

    @Autowired
    private IOrdenService ordenService;

    Logger log = LoggerFactory.getLogger(AdministradorController.class);

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

    @GetMapping("/ordenes")
    public String ordenes (Model model){
        List<Orden> compras = ordenService.findAll();
        log.info("Ordenes {}",compras);
        model.addAttribute("ordenes",compras);
        return "administrador/ordenes";
    }

    @GetMapping("/detalleorden/{id}")
    public String detallecompra(@PathVariable Integer id, Model model){
        log.info("id de la orden {} ",id);
        Optional<Orden> orden = ordenService.findById(id);
        model.addAttribute("detalleorden",orden.get().getDetalleOrden());
        return "administrador/detalleorden";
    }
}
