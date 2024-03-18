package com.capstone.onlinemoviebooking.repository;

import com.capstone.onlinemoviebooking.model.SeatMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreanMapRepositoryI extends JpaRepository<SeatMap,Long> {
}
