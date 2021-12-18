package com.kaczart.moviesweb.movies.service;

import com.kaczart.moviesweb.movies.exceptions.MovieException;
import com.kaczart.moviesweb.movies.model.Movie;
import com.kaczart.moviesweb.user.model.ResponseUser;
import com.kaczart.moviesweb.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GameService {

    public static final String THE_LIST_PROVIDED_IS_EMPTY = "The list provided is empty";
    private MovieService movieService;
    private UserService userService;

    public GameService(MovieService movieService, UserService userService) {
        this.movieService = movieService;
        this.userService = userService;
    }

    public List<Movie> takeRandomMovies(int amount) {
        List<Movie> allMovies = movieService.findAllMovies();
        Collections.shuffle(allMovies);
        boolean isOutOfTheLimit = amount > allMovies.size();
        int moviesAmount = isOutOfTheLimit ? allMovies.size() : amount;
        return allMovies.subList(0, moviesAmount);
    }

    public ResponseUser calculateScoreAndSavePoints(List<Movie> userMovies, String userName) {
        ResponseUser user = userService.getUserByName(userName);
        int score = calculateScore(userMovies);
        return userService.addPoints(user, score);
    }

    private int calculateScore(List<Movie> userMovies) {
        int score = 0;
        for (int i = 0; i < userMovies.size() - 1; i++) {
            boolean point = userMovies.get(i).getYear() <= userMovies.get(i + 1).getYear();
            score = point ? score + 1 : score;
        }
        return score;
    }

    private void validateMovies(List<Movie> userMovies) throws MovieException {
        if (userMovies.isEmpty()) {
            throw new MovieException(THE_LIST_PROVIDED_IS_EMPTY);
        }
    }

}

