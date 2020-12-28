package com.example.portfolio.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.DestinationRepository;
import com.example.portfolio.model.entity.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class DestinationService {

  @Autowired
  DestinationRepository destinationRepos;

  public List<Destination> findAll() {
    return destinationRepos.findAll();
  }

  public Destination findById(int destinationId) {
    Optional <Destination> result = destinationRepos.findById(destinationId);
    Destination destination = result.get();
    return destination;
  }
}
