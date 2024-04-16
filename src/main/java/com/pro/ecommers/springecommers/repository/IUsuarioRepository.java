package com.pro.ecommers.springecommers.repository;

import com.pro.ecommers.springecommers.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
}
