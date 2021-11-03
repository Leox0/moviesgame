package com.kaczart.moviesweb.movies.controller;

import com.kaczart.moviesweb.movies.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public void test (){
        String imdbIdTest = "tt4154796";
        movieService.createMovie(imdbIdTest);
    }
}
