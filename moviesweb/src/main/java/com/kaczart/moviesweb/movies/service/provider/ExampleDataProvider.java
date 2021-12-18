package com.kaczart.moviesweb.movies.service.provider;

import com.kaczart.moviesweb.movies.model.Movie;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleDataProvider implements MovieProvider {
    @Override
    public List<Movie> getMovies() {
        return List.of(
                new Movie("Random movie1", 1990),
                new Movie("Random movie2", 1991),
                new Movie("Random movie3", 1992),
                new Movie("Random movie4", 1993),
                new Movie("Random movie5", 1994),
                new Movie("Random movie6", 1995),
                new Movie("Random movie7", 1996),
                new Movie("Random movie8", 1997),
                new Movie("Random movie9", 1998),
                new Movie("Random movie10", 1999),
                new Movie("Random movie11", 2000),
                new Movie("Random movie12", 2001),
                new Movie("Random movie13", 2002),
                new Movie("Random movie14", 2003),
                new Movie("Random movie15", 2004),
                new Movie("Random movie16", 2005),
                new Movie("Random movie17", 2006),
                new Movie("Random movie18", 2007),
                new Movie("Random movie19", 2008),
                new Movie("Random movie20", 2009),
                new Movie("Random movie21", 2010),
                new Movie("Random movie22", 2011),
                new Movie("Random movie23", 2012),
                new Movie("Random movie24", 2013),
                new Movie("Random movie25", 2014),
                new Movie("Random movie26", 2015),
                new Movie("Random movie27", 2016),
                new Movie("Random movie28", 2017),
                new Movie("Random movie29", 2018),
                new Movie("Random movie30", 2019)
        );
    }
}
