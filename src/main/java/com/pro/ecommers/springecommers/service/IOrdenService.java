package com.pro.ecommers.springecommers.service;

import com.pro.ecommers.springecommers.model.Orden;
import com.pro.ecommers.springecommers.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IOrdenService {
    public Orden save(Orden orden);
    public List<Orden>findAll();
    String gerararNumeroOrden();
    List<Orden>findByUsuario(Usuario usuario);
    Optional<Orden> findById (Integer id);

}
