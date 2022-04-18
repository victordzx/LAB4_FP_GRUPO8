package com.example.LAB4_FP_GRUPO8.repository;

import com.example.LAB4_FP_GRUPO8.entity.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationsRepository extends JpaRepository<Locations,Integer> {
}
