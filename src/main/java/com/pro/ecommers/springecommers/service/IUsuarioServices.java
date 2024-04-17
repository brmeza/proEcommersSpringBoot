package com.pro.ecommers.springecommers.service;

import com.pro.ecommers.springecommers.model.Usuario;

import java.util.Optional;

public interface IUsuarioServices {
    Optional<Usuario> findById(Integer id);
    Usuario save(Usuario usuario);
}
