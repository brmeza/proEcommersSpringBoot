package com.pro.ecommers.springecommers.repository;

import com.pro.ecommers.springecommers.model.Producto;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    //podria ser productoDAO tambine

}


