package com.kaczart.moviesweb.movies.service;

import com.kaczart.moviesweb.movies.entity.MovieEntity;
import com.kaczart.moviesweb.movies.exceptions.MovieException;
import com.kaczart.moviesweb.movies.model.Movie;
import com.kaczart.moviesweb.movies.repository.MovieRepository;
import com.kaczart.moviesweb.movies.service.provider.MovieProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieServiceTestUnit {

    private MovieService movieService;

    private MovieProvider movieProviderMock;

    private MovieRepository movieRepositoryMock;

    @BeforeEach
    void setUp() {
        movieProviderMock = Mockito.mock(MovieProvider.class);
        movieRepositoryMock = Mockito.mock(MovieRepository.class);
        movieService = new MovieService(movieProviderMock, movieRepositoryMock);
    }

    @Test
    public void shouldSaveMovieToDB() throws MovieException {
        //given
        List<Movie> movies = List.of(
                new Movie("Movie 1", 2001),
                new Movie("Movie 2", 2002),
                new Movie("Movie 3", 2004)
        );
        int expectedAmount = movies.size();

        Mockito.when(movieProviderMock.getMovies()).thenReturn(movies);

        //when
        movieService.saveMovies();

        //then
        Mockito.verify(movieRepositoryMock, Mockito.times(expectedAmount)).save(Mockito.any());
    }

    @Test
    public void shouldGetMovieByTitle() throws MovieException {
        //given
        String movieTitle = "Avengers";
        MovieEntity movieEntity = new MovieEntity(1L, movieTitle,2000);
        Mockito.when(movieRepositoryMock.findByTitle(movieTitle)).thenReturn(Optional.of(movieEntity));

        //when
        Movie serviceMovie = movieService.getMovieFromDBByTitle(movieTitle);

        //then
        assertEquals(movieEntity.toView(),serviceMovie);
    }

}
