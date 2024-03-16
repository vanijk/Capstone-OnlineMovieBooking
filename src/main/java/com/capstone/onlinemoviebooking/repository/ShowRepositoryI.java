package com.capstone.onlinemoviebooking.repository;

import com.capstone.onlinemoviebooking.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepositoryI extends JpaRepository<Show,Long> {
}
