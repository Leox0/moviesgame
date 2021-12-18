package com.kaczart.moviesweb.movies.controller;

import com.kaczart.moviesweb.movies.exceptions.MovieException;
import com.kaczart.moviesweb.movies.model.Movie;
import com.kaczart.moviesweb.movies.model.ScoreData;
import com.kaczart.moviesweb.movies.service.GameService;
import com.kaczart.moviesweb.user.model.RequestUser;
import com.kaczart.moviesweb.user.model.ResponseUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("/game")
public class GameController {

    private GameService gameService;
    private static final Integer DEFAULT_AMOUNT = 5;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/createScore")
    public ResponseEntity<ResponseUser> createScore(@RequestBody @Valid ScoreData scoreData) {

        ResponseUser userScore = gameService.calculateScoreAndSavePoints(scoreData.getMovies(), scoreData.getUserName());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userScore);
    }

    @GetMapping(value = {"/runGame", "/runGame/{amount}"})
    public ResponseEntity<List<Movie>> takeRandomMovie(@PathVariable Optional<Integer> amount) {

        List<Movie> movies = amount.map(getOrUseDefault)
                .map(a -> gameService.takeRandomMovies(a))
                .orElseGet(() -> gameService.takeRandomMovies(DEFAULT_AMOUNT));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(movies);
    }

    private final Function<Integer, Integer> getOrUseDefault = (amount) ->
            amount > DEFAULT_AMOUNT ? amount : DEFAULT_AMOUNT;

}
