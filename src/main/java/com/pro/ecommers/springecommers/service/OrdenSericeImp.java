package com.pro.ecommers.springecommers.service;

import com.pro.ecommers.springecommers.model.Orden;
import com.pro.ecommers.springecommers.repository.IOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenSericeImp implements IOrdenService{

    @Autowired
    private IOrdenRepository ordenRepository;


    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }
}
