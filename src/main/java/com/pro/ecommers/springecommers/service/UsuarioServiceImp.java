package com.pro.ecommers.springecommers.service;

import com.pro.ecommers.springecommers.model.Usuario;
import com.pro.ecommers.springecommers.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImp implements IusuarioServices{

    @Autowired
    private IUsuarioRepository UsuarioRepository;


    @Override
    public Optional<Usuario> findById(Integer id) {
        return UsuarioRepository.findById(id);
    }
}
