package com.capstone.onlinemoviebooking.repository;

import com.capstone.onlinemoviebooking.model.BookedSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookedSeatsRepositoryI extends JpaRepository<BookedSeats, Long> {

    @Query("FROM BookedSeats where screenId = screenId and screeningTime = screeningTime and screeningDate = screeningDate")
    List<BookedSeats> findSeatsBookedByScreenDateTime(int screenId, java.sql.Date screeningDate, String screeningTime);
}
