package com.capstone.onlinemoviebooking.dto;

import com.capstone.onlinemoviebooking.model.Show;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private String title;
    private long theatreId;
    private String theaterName;
    private long screenId;
    private String screenName;
    private double ticketPrice;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private List<Show> shows;
}
