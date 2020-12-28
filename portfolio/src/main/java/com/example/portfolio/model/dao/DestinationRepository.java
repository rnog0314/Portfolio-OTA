package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.Destination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer> {

}
