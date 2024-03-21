package com.capstone.onlinemoviebooking.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BookedSeats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long screenId;
    private java.sql.Date screeningDate;
    private String screeningTime;
    private String seatName;
    private String status;
    private long userId;
    public BookedSeats(long screenId, Date screeningDate, String screeningTime, String seatName, String status,long userId) {
        this.screenId = screenId;
        this.screeningDate = screeningDate;
        this.screeningTime = screeningTime;
        this.seatName = seatName;
        this.status = status;
        this.userId = userId;
    }
}
