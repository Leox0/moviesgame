package com.kaczart.moviesweb.movies.service;

import com.kaczart.moviesweb.movies.entity.MovieEntity;
import com.kaczart.moviesweb.movies.model.Movie;
import com.kaczart.moviesweb.movies.repository.MovieRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private MovieProvider movieProvider;
    private MovieRepository movieRepository;

    public MovieService(MovieProvider movieProvider, MovieRepository movieRepository) {
        this.movieProvider = movieProvider;
        this.movieRepository = movieRepository;
    }

    public void createMovie(String imdbId) {
        Movie movie = movieProvider.getMovie(imdbId);
        MovieEntity movieEntity = new MovieEntity().builder()
                .title(movie.getTitle())
                .year(movie.getYear())
                .build();
        movieRepository.save(movieEntity);
    }


}
