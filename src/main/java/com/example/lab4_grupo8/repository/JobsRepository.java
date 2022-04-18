package com.example.lab4_grupo8.repository;

import com.example.lab4_grupo8.entity.Jobs;
import com.example.laboratorio4.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepository extends JpaRepository<Jobs, String> {
}
