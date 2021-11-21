package com.kaczart.moviesweb.movies.controller;

import com.kaczart.moviesweb.movies.exceptions.MovieException;
import com.kaczart.moviesweb.movies.model.Movie;
import com.kaczart.moviesweb.movies.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> findAllMovies() {
        List<Movie> movieList = movieService.findAllMovies();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(movieList);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Movie> getMovieByTitle(@PathVariable(value = "title") String title) throws MovieException {
        Movie movie = movieService.getMovieFromDBByTitle(title);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(movie);
    }

    @GetMapping("/save")
    public ResponseEntity<List<Movie>> saveMoviesToDb() throws MovieException {
        List<Movie> movieList = movieService.saveMovies();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(movieList);
    }
}
