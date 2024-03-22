package com.capstone.onlinemoviebooking.repository;

import com.capstone.onlinemoviebooking.model.Screen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenRepositoryI extends CrudRepository<Screen, Long> {
    @Query( value = "SELECT * FROM Screen s WHERE screenDate between startDate and EndDate",
            nativeQuery = true)
    List<Screen> findScreenByScreeningDate(java.sql.Date screendate);
    @Query( value = "SELECT * FROM Screen s WHERE theatre_theatre_id = :theaterId",
            nativeQuery = true)
    List<Screen> findByThaeterId(long theaterId);
   // List<Screen> findScreenByStartDateGreaterThanEqualAndEndDateIsLessThanEqual(java.sql.Date date);
}
