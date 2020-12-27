package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.Destinations;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destinations, Integer> {

}
