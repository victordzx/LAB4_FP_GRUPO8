package com.example.lab4_grupo8.repository;

import com.example.lab4_grupo8.entity.Locations;
import com.example.laboratorio4.entity.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationsRepository extends JpaRepository<Locations,Integer> {
}
