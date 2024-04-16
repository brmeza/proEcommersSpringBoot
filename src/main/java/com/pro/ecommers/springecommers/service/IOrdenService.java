package com.pro.ecommers.springecommers.service;

import com.pro.ecommers.springecommers.model.Orden;

import java.util.List;

public interface IOrdenService {
    public Orden save(Orden orden);
    public List<Orden>findAll();

}
