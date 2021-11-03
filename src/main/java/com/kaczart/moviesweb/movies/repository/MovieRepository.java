package com.kaczart.moviesweb.movies.repository;

import com.kaczart.moviesweb.movies.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

}
