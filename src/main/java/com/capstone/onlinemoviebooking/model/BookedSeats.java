package com.capstone.onlinemoviebooking.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BookedSeats {
    @Id
    private long id;
    private int screenId;
    private java.sql.Date screeningDate;
    private String screeningTime;
    private String seatName;
    private String status;
}
