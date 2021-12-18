package com.kaczart.moviesweb.movies.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreData {

    @NotEmpty
    List<Movie> movies;
    @NotEmpty
    String userName;

}
