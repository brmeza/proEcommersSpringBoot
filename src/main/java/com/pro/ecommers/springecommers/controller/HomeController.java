package com.pro.ecommers.springecommers.controller;

import com.pro.ecommers.springecommers.model.DetalleOrden;
import com.pro.ecommers.springecommers.model.Orden;
import com.pro.ecommers.springecommers.model.Producto;
import com.pro.ecommers.springecommers.model.Usuario;
import com.pro.ecommers.springecommers.service.IDetalleOrdenService;
import com.pro.ecommers.springecommers.service.IOrdenService;
import com.pro.ecommers.springecommers.service.IUsuarioServices;
import com.pro.ecommers.springecommers.service.IProductoService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IUsuarioServices usuarioServices;

    @Autowired
    private IOrdenService ordenService;

    @Autowired
    private IDetalleOrdenService detalleOrdenService;

    //almacernar detalles de la orden
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();
    //datos de la orden
    Orden orden = new Orden();

    @GetMapping("")
    public String Home(Model model, HttpSession session){
        log.info("Seccion del usuario: {}",session.getAttribute("idUsuario"));
        model.addAttribute("productos",productoService.findAll());
        //sesion
        model.addAttribute("sesion",session.getAttribute("idUsuario"));
        return "usuario/home";
    }

    @GetMapping("productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model){
        log.info("ID enviado como parametro {}",id);
        Producto producto;
        Optional<Producto> productoOptional = productoService.get(id);
        producto = productoOptional.get();
        model.addAttribute("producto",producto);
        return "usuario/productohome";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model){
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();
        double sumaTotal = 0;
        Optional<Producto> optionalProducto = productoService.get(id);
        log.info("producto añadido {}",optionalProducto.get());
        log.info("cantidad {}",cantidad);
        producto=optionalProducto.get();

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio()*cantidad);
        detalleOrden.setProducto(producto);

        //validar que el producto no se añada dos veces
        Integer idProducto = producto.getId();
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId()==idProducto);
        if(!ingresado){
            detalles.add(detalleOrden);
        }

        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
        orden.setTotal(sumaTotal);
        model.addAttribute("cart",detalles);
        model.addAttribute("orden", orden);
        return "usuario/carrito";
    }

    //quietar un prducto dfe carrito

    @GetMapping("/delete/cart/{id}")
    public String deleteProductoCar(@PathVariable Integer id, Model model){
        List<DetalleOrden> ordenNueva = new ArrayList<DetalleOrden>();
        for (DetalleOrden detalleOrden: detalles){
            if(detalleOrden.getProducto().getId()!=id){
                ordenNueva.add(detalleOrden);
            }
        }
        //nueva lista con productos restantes
        detalles=ordenNueva;
        double sumaTotal=0;
        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        return "usuario/carrito";
    }

    @GetMapping("/getCart")
    public String getCart(Model model, HttpSession sesion){
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        //sesion
        model.addAttribute("sesion",sesion.getAttribute("idUsuario"));
        return "/usuario/carrito";
    }

    @GetMapping("/order")
    public String order(Model model, HttpSession session){


        Usuario usuario = usuarioServices.findById(Integer.parseInt(session.getAttribute("idUsuario").toString())).get();

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario",usuario);
        return "usuario/resumenorden";
    }

    //guardar la orden
    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session){
        Date fechaCreacion = new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenService.gerararNumeroOrden());
        //usuario
        Usuario usuario = usuarioServices.findById(Integer.parseInt(session.getAttribute("idUsuario").toString())).get();
        orden.setUsuario(usuario);

        //guardar Orden
        ordenService.save(orden);

        //guardar detalles
        for (DetalleOrden dt:detalles){
            dt.setOrden(orden);
            detalleOrdenService.save(dt);
        }

        //limpiar lista Orden
        orden = new Orden();
        detalles.clear();

        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchProduct(@RequestParam String nombre, Model model){
        log.info("nombre del producto {}",nombre);
        List<Producto> productos = productoService.findAll().stream().filter(p -> p.getNombre().contains(nombre)).collect(Collectors.toList());
        model.addAttribute("productos", productos);

        return "usuario/home";
    }


}
