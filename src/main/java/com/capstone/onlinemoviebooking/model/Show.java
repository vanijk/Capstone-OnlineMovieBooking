package com.capstone.onlinemoviebooking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "SHOW")
@Getter
@Setter
public class Show {
    @Id
    @Column(name = "SHOW_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long show_id;

    private java.sql.Date startDate;
    private java.sql.Date endDate;

    private String showTime;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Screen screen;

}
