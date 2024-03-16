package com.capstone.onlinemoviebooking.repository;

import com.capstone.onlinemoviebooking.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepositoryI extends CrudRepository<Seat,Long> {
    @Query( value = "SELECT * FROM Seat s WHERE s.screen_screen_id = 1 ",
            nativeQuery = true)
    List<Seat> findByScreen(Long screen);
}
