package com.kaczart.moviesweb.movies.service.provider;

import com.kaczart.moviesweb.movies.entity.MovieEntity;
import com.kaczart.moviesweb.movies.model.Movie;

import java.util.List;

public interface MovieProvider {

    List<Movie> getMovies();

}
