package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Admin findByAdminNameAndPassword(String adminName, String password);

}
