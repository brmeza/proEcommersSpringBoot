package com.pro.ecommers.springecommers.repository;

import com.pro.ecommers.springecommers.model.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer> {
}
