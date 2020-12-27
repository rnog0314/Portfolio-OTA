package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.Destinations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DestinationRepository extends JpaRepository<Destinations, Integer> {

}
