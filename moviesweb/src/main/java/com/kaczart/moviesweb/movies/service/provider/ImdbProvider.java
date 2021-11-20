package com.kaczart.moviesweb.movies.service.provider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaczart.moviesweb.movies.model.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ImdbProvider implements MovieProvider {

    private static final String baseUrl = "https://imdb8.p.rapidapi.com/title";
    private static final String getBase = "/get-base?tconst=";
    private static final String getMostePopularMovies = "/get-most-popular-movies";

    @Value("${x-rapidapi-host}")
    private String host;
    @Value("${x-rapidapi-key}")
    private String key;

    private static JsonNode jsonNode;

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    public ImdbProvider(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Movie> getMovies() {
        List<String> moviesId = getMoviesIdFromApi();
        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i < 2; i++) { //i<* because imdb licence //ultimately it should be movieIdList.size()
            String s = moviesId.get(i).substring(7);
            Movie movie = getMovieFromApi(s);
            movies.add(movie);
        }
        return movies;
    }

    private List<String> getMoviesIdFromApi() {
        String url = baseUrl + getMostePopularMovies;
        ResponseEntity<List> httpResponse = getHttpResponse(url, List.class);
        return httpResponse.getBody();
    }

    private Movie getMovieFromApi(String imdbId) {
        String url = baseUrl + getBase + imdbId;
        ResponseEntity<Movie> httpResponse = getHttpResponse(url, Movie.class);
        return httpResponse.getBody();
    }

    private <T> ResponseEntity<T> getHttpResponse(String url, Class<T> aClass) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("x-rapidapi-host", host);
        headers.add("x-rapidapi-key", key);
        HttpEntity entity = new HttpEntity(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, aClass);
    }


}
