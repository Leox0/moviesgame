package com.kaczart.moviesweb.movies.entity;

import com.kaczart.moviesweb.movies.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Movies")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieEntity {

    public static final String INCORRECT_FORMAT_OF_YEAR = "Incorrect format of year";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String title;

    @NotNull
    private int year;

    public Movie toView(){
        return Movie.builder()
                .title(title)
                .year(year)
                .build();
    }

}
