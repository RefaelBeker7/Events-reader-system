package com.refael.events_reader_system.repository;


import com.refael.events_reader_system.model.ProductEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductEventRepository extends JpaRepository<ProductEvent, Long> {
}