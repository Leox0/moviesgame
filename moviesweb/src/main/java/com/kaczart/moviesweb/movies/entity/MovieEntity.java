package com.kaczart.moviesweb.movies.entity;

import com.kaczart.moviesweb.movies.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Movies")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String title;

    private int year;

    public Movie toView(){
        return Movie.builder()
                .title(title)
                .year(year)
                .build();
    }

}
