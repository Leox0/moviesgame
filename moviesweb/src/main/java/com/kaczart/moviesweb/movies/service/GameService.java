package com.kaczart.moviesweb.movies.service;

import com.kaczart.moviesweb.movies.model.Movie;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class GameService {

    private MovieService movieService;

    public GameService(MovieService movieService) {
        this.movieService = movieService;
    }
    public List<Movie> takeRandomMovies(int moviesAmount) {
        Random rand = new Random();
        List<Movie> allMovies = movieService.findAllMovies();
        Collections.shuffle(allMovies);
        return allMovies.subList(0, moviesAmount);
    }

}

