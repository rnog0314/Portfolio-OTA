package com.example.portfolio.model.dao;

import java.util.List;

import com.example.portfolio.model.entity.Destination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer> {

	@Query(value = "SELECT d FROM Destination d INNER JOIN FETCH d.destinationDetail")
	List<Destination> findAllDestination();

}
