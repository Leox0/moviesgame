package com.kaczart.moviesweb.movies.service;

import com.kaczart.moviesweb.movies.bootstrap.MovieResolver;
import com.kaczart.moviesweb.movies.entity.MovieEntity;
import com.kaczart.moviesweb.movies.exceptions.MovieException;
import com.kaczart.moviesweb.movies.model.Movie;
import com.kaczart.moviesweb.movies.repository.MovieRepository;
import com.kaczart.moviesweb.movies.service.provider.MovieProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MovieServiceTest {

    @Autowired
    MovieService movieService;

    @Autowired
    MovieRepository movieRepository;

    @MockBean
    MovieProvider mockMovieProvider;

    @MockBean
    MovieResolver movieResolver;

    @Test
    public void shouldSaveMovieToDB() throws MovieException {
        //given
        List<Movie> moviesExpected = List.of(
                new Movie("Movie 1", 2001),
                new Movie("Movie 2", 2002),
                new Movie("Movie 3", 2004)
        );
        Mockito.when(mockMovieProvider.getMovies()).thenReturn(moviesExpected);

        //when
        movieService.saveMovies();

        //then
        List<Movie> movies = movieRepository.findAll().stream()
                .map(MovieEntity::toView)
                .collect(Collectors.toList());
        assertEquals(moviesExpected, movies);

    }
}
