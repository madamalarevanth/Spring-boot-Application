package com.movierating.moviecatalogservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.movierating.moviecatalogservices.models.CatalogItem;
import com.movierating.moviecatalogservices.models.Movie;
import com.movierating.moviecatalogservices.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@Service
public class MovieInfo {

	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod= "getFallbackCatalogItem")
	public CatalogItem getCatalogItem(Rating rating) {

		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
	}
    
	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie Not Found","", rating.getRating());
	}
}
