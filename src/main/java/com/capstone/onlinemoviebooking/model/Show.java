package com.capstone.onlinemoviebooking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "SHOW")
@Getter
@Setter
@NoArgsConstructor
public class Show {
    @Id
    @Column(name = "SHOW_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long showId;

    private java.sql.Date startDate;
    private java.sql.Date endDate;

    private String showTime;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Screen screen;

    public Show(Movie movie, Screen screen,String showTime, Date startDate, Date endDate  ) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.showTime = showTime;
        this.movie = movie;
        this.screen = screen;
    }
}
