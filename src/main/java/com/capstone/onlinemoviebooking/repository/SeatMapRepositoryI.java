package com.capstone.onlinemoviebooking.repository;

import com.capstone.onlinemoviebooking.model.SeatMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SeatMapRepositoryI extends JpaRepository<SeatMap,Long> {
    @Modifying
    @Transactional
    @Query( value = "update seat_map set class_name = 'seat' where class_name = 'booked' ",
            nativeQuery = true)
    void setSeatsBack();

}
