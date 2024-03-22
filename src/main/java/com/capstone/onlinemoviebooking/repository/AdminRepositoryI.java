package com.capstone.onlinemoviebooking.repository;

import com.capstone.onlinemoviebooking.dto.MovieDTO;
import com.capstone.onlinemoviebooking.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepositoryI extends JpaRepository<Movie,String> {
}
