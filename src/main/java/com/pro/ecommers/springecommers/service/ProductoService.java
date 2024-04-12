package com.pro.ecommers.springecommers.service;

import com.pro.ecommers.springecommers.model.Producto;

import java.util.Optional;

public interface ProductoService {
    //definir metodos CRUD para la table producto;

    public Producto Save (Producto producto);
    public Optional<Producto> get(Integer id);
    //opcional nos la opcion si el objeto que llamamos de la base existe

    public void update (Producto producto);
    public  void delete(Integer id);
}