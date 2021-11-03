package com.kaczart.moviesweb.movies.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private String title;

    private int year;

    //private Double raiting;

}
