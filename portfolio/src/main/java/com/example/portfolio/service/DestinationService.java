package com.example.portfolio.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.DestinationRepository;
import com.example.portfolio.model.entity.Destinations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class DestinationService {

  @Autowired
  DestinationRepository destinationRepos;

  public List<Destinations> findAll() {
		return destinationRepos.findAll();
	}
}
