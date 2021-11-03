package com.kaczart.moviesweb.movies.service;

import com.kaczart.moviesweb.movies.entity.MovieEntity;
import com.kaczart.moviesweb.movies.model.Movie;

public interface MovieProvider {

    Movie getMovie(String imdbId);

}
