package com.capstone.onlinemoviebooking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "SCREEN")
@Getter
@Setter
public class Screen {

    @Id
    @Column(name = "SCREEN_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long screenId;
    private  String screenName;
    private double ticketPrice;
    @ManyToOne
    private Theatre theatre;
}