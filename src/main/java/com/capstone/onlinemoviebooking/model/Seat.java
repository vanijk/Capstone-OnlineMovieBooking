package com.capstone.onlinemoviebooking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SEAT")
public class Seat {
    @Id
    @Column(name = "SEAT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long seat_id;
    private char rowName;
    private int rowNumber;

    private String status;
    @ManyToOne
    private Screen screen;
    public long getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(long seat_id) {
        this.seat_id = seat_id;
    }

    public char getRow() {
        return rowName;
    }

    public void setRow(char rowName) {
        this.rowName = rowName;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public char getRowName() {
        return rowName;
    }

    public void setRowName(char rowName) {
        this.rowName = rowName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }
}

