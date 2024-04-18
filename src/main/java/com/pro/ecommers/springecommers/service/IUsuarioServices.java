package com.pro.ecommers.springecommers.service;

import com.pro.ecommers.springecommers.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioServices {
    List<Usuario> findAll();
    Optional<Usuario> findById(Integer id);
    Usuario save(Usuario usuario);
    Optional<Usuario> findByEmail (String email);


}
