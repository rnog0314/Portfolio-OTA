package com.example.portfolio.service;

import java.util.List;

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

  /**
   * デスティネーション全取得
   * @return List<Destination> デスティネーション一覧
   */
  public List<Destination> findAll() {
    return destinationRepos.findAllDestination();
  }

  /**
   * デスティネーション取得
   * @param destinationId デスティネーションID
   * @return Destination デスティネーション
   */
  public Destination findById(int id) {
    return destinationRepos.findDestinationById(id);
  }
}
