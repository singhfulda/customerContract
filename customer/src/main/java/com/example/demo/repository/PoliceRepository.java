package com.example.demo.repository;

import com.example.demo.domain.Police;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface PoliceRepository extends JpaRepository<Police, Long> {
}