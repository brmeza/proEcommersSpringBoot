package com.pro.ecommers.springecommers.repository;

import com.pro.ecommers.springecommers.model.Orden;
import com.pro.ecommers.springecommers.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdenRepository extends JpaRepository<Orden,Integer> {
    List<Orden> findByUsuario(Usuario usuario);
}
