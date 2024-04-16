package com.pro.ecommers.springecommers.repository;

import com.pro.ecommers.springecommers.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdenRepository extends JpaRepository<Orden,Integer> {
}
