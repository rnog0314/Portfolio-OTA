package com.example.portfolio.service;

import java.util.Optional;

import com.example.portfolio.model.dao.DestinationDetailRepository;
import com.example.portfolio.model.entity.DestinationDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DestinationDetailService {

  @Autowired
  private DestinationDetailRepository destinationDetailRepos;

	public DestinationDetail findById(int destinationId) {
    Optional <DestinationDetail> result =  destinationDetailRepos.findById(destinationId);
    DestinationDetail destinationDetail =  result.get();
    return destinationDetail;
	}

}
