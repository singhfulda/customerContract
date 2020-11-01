package com.example.demo.PoliceRepository;

import com.example.demo.domain.Police;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliceRepository extends JpaRepository<Police, Long> {
}
