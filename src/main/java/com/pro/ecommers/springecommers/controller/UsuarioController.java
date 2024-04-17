package com.pro.ecommers.springecommers.controller;

import com.pro.ecommers.springecommers.model.Usuario;
import com.pro.ecommers.springecommers.service.IUsuarioServices;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private IUsuarioServices usuarioService;
    @Autowired
    private HttpSession httpSession;

    @GetMapping("/registro")
    public String create(){
        return "usuario/registro";
    }

    @PostMapping("/save")
    public String save(Usuario usuario){
        log.info("Usuario registro{}",usuario);
        usuario.setTipo("USER");
        usuarioService.save(usuario);
        return "redirect:/";
    }
    @GetMapping("/login")
    public String login(){

        return "usuario/login";
    }

    @PostMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session){
        log.info("Acceder: {}", usuario);
        Optional<Usuario> user = usuarioService.findByEmail(usuario.getEmail());
        //og.info("usuario de db {}",user.get());

        if (user.isPresent()) {
            session.setAttribute("idUsuario",user.get().getId());
            if(user.get().getTipo().equals("ADMIN")){
                return "redirect:/administrador";
            }else {
                return "redirect:/";
            }
        }else {
            log.info("Usuario no existe");
        }
        return "redirect:/";
    }


    @GetMapping("/compras")
    public String obtenerCompras(Model model, HttpSession sesion){
        model.addAttribute("sesion", sesion.getAttribute("idUsuario"));
        return "usuario/compras";

    }

}
