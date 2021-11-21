package com.kaczart.moviesweb.movies.bootstrap;

import com.kaczart.moviesweb.movies.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class MovieResolver implements CommandLineRunner {

    private final MovieService movieService;

    @Override
    public void run(String... args) throws Exception {
        movieService.saveMovies();
    }

}
