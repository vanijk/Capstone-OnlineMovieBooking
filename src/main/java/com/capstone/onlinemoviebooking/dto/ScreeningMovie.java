package com.capstone.onlinemoviebooking.dto;

import com.capstone.onlinemoviebooking.model.Movie;
import com.capstone.onlinemoviebooking.model.Screen;
import com.capstone.onlinemoviebooking.model.Show;
import com.capstone.onlinemoviebooking.model.Theatre;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ScreeningMovie {

    private Movie movie;
    private long theatreId;
    private String theaterName;
    private String theaterAddress;
    private long screenId;
    private String ScreenName;
    private List<Show> shows;
}
