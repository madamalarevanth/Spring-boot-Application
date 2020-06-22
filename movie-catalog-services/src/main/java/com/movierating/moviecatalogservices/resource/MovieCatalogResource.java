package com.movierating.moviecatalogservices.resource;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import com.movierating.moviecatalogservices.models.CatalogItem;
import com.movierating.moviecatalogservices.models.Movie;
import com.movierating.moviecatalogservices.models.Rating;
import com.movierating.moviecatalogservices.models.userRatings;
import com.movierating.moviecatalogservices.services.MovieInfo;
import com.movierating.moviecatalogservices.services.userRatingInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

	@RestController
	@RequestMapping("/catalog")
	public class MovieCatalogResource {
		
		@Autowired
	    private RestTemplate restTemplate;
		
		@Autowired
		MovieInfo movieinfo;
		
		@Autowired
		userRatingInfo  userRatingInfo;
		

	    @RequestMapping("/{userId}")
	    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {		
			
			//RestTemplate restTemplate = new RestTemplate();
			//get all rated movie ids
			userRatings userRating = userRatingInfo.getUserRatings(userId);
			
			return userRating.getRatings().stream()
	                .map(rating -> movieinfo.getCatalogItem(rating))
	                .collect(Collectors.toList());

	    }
}
        

/*
Alternative WebClient way
Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/"+ rating.getMovieId())
.retrieve().bodyToMono(Movie.class).block();
*/