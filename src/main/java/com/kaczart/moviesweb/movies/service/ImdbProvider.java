package com.kaczart.moviesweb.movies.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaczart.moviesweb.movies.model.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class ImdbProvider implements MovieProvider{

    private static final String baseUrl = "https://imdb8.p.rapidapi.com/title";
    private static final String getBase = "/get-base?tconst=";

    @Value("${x-rapidapi-host}")
    private String host ;
    @Value("${x-rapidapi-key}")
    private String key;

    private static JsonNode jsonNode = null;

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    public ImdbProvider(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public Movie getMovie(String imdbId) {
        String url = baseUrl + getBase + imdbId;
        ResponseEntity<Movie> exchange = getHttpResponse(url);
        return exchange.getBody();
    }

    private ResponseEntity<Movie> getHttpResponse(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("x-rapidapi-host", host);
        headers.add("x-rapidapi-key", key);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<Movie> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, Movie.class);
        return exchange;
    }


}
