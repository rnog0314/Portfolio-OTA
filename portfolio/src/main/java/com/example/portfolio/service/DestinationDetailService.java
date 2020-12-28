package com.example.portfolio.service;

import java.util.Optional;

import com.example.portfolio.model.dao.DestinationDetailRepository;
import com.example.portfolio.model.entity.DestinationDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DestinationDetailService {

  @Autowired
  private DestinationDetailRepository destinationDetailRepos;

	public DestinationDetails findById(int destinationId) {
    Optional <DestinationDetails> result =  destinationDetailRepos.findById(destinationId);
    DestinationDetails destinationDetail =  result.get();
    return destinationDetail;
	}

}
