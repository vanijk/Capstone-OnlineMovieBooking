package com.capstone.onlinemoviebooking.repository;

import com.capstone.onlinemoviebooking.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepositoryI extends JpaRepository<Theatre,Long> {
}
