package com.kaczart.moviesweb.movies.service;

import com.kaczart.moviesweb.movies.entity.MovieEntity;
import com.kaczart.moviesweb.movies.exceptions.MovieException;
import com.kaczart.moviesweb.movies.model.Movie;
import com.kaczart.moviesweb.movies.repository.MovieRepository;
import com.kaczart.moviesweb.movies.service.provider.MovieProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    public static final String MOVIE_LIST_IS_NOT_CORRECT = "Movie list is not correct";
    public static final String MOVIE_DOESN_T_EXISTS = "Movie doesn't exist";
    private MovieProvider movieProvider;
    private MovieRepository movieRepository;

    public MovieService(MovieProvider movieProvider, MovieRepository movieRepository) {
        this.movieProvider = movieProvider;
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(MovieEntity::toView)
                .collect(Collectors.toList());
    }

    public Movie getMovieFromDBByTitle(String title) throws MovieException {
        MovieEntity movieEntity = movieRepository.findByTitle(title).orElseThrow(() -> new MovieException(MOVIE_DOESN_T_EXISTS));
        return movieEntity.toView();
    }

    public List<Movie> saveMovies() throws MovieException {
        List<Movie> movies = getMoviesFromProvider();

        return movies.stream()
                .map(this::createMovieInDB)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<Movie> getMoviesFromProvider() throws MovieException {
        List<Movie> movies = movieProvider.getMovies();
        if (movies.size() < 1) {
            throw new MovieException(MOVIE_LIST_IS_NOT_CORRECT);
        }
        return movies;
    }

    private Optional<Movie> createMovieInDB(Movie movie) {
        MovieEntity movieEntity = MovieEntity.builder()
                .title(movie.getTitle())
                .year(movie.getYear())
                .build();
        try {
            movieRepository.save(movieEntity);
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.of(movieEntity.toView());
    }
}
