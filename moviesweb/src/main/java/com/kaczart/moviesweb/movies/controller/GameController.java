package com.kaczart.moviesweb.movies.controller;

import com.kaczart.moviesweb.movies.model.Movie;
import com.kaczart.moviesweb.movies.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/game")
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(value = {"", "/{amount}"})
    public ResponseEntity<List<Movie>> takeRandomMovie(@PathVariable Optional<Integer> amount) {
        List<Movie> movies;
        if (amount.isPresent()) {
            movies = gameService.takeRandomMovies(amount.get());
        } else {
            movies = gameService.takeRandomMovies(5);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(movies);
    }
}
