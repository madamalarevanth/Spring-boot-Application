package com.movierating.movieratings.services.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movierating.movieratings.services.model.Rating;
import com.movierating.movieratings.services.model.userRatings;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

    @RequestMapping("/movies/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }
    
    @RequestMapping("/user/{userId}")
    public userRatings getuserRatings(@PathVariable("userId") String userId) {
    	List<Rating> ratings = Arrays.asList(
				new Rating("100",4),
				new Rating("200",3)
				);
    	
    	userRatings userRating = new userRatings();
    	userRating.setRatings(ratings);
    	
    	return userRating;
    }


}