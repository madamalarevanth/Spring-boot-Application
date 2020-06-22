package com.movierating.moviecatalogservices.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.movierating.moviecatalogservices.models.Rating;
import com.movierating.moviecatalogservices.models.userRatings;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class userRatingInfo {
	
	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod= "getFallbackUserRatings"/*,
			commandProperties = {
					@HystrixProperty(name= "execution.isolation.thread.timeoutInMilliseconds",value="2000"),
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="5"),
					@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
					@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000")			
			}*/
			)
	public userRatings getUserRatings(String userId) {
		return restTemplate.getForObject("http://movie-ratings-service/ratingsdata/user/" + userId, userRatings.class);
	}
    
	public userRatings getFallbackUserRatings(String userId) {
			userRatings ur = new userRatings();
			ur.setUserId(userId);
			ur.setRatings(Arrays.asList( new Rating("0",0)));
			return ur;
	}

}
