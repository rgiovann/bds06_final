package com.devsuperior.movieflix.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired ReviewRepository reviewRepository;
	
	@Transactional
	public ReviewDTO insert(ReviewDTO reviewDTO) {
		UserDTO userDTO= authService.authenticated();
		Optional<User> objUser = userRepository.findById(userDTO.getId());
		User entityUser = objUser.orElseThrow(() -> new ResourceNotFoundException("Error. User Id not found: " 
                + userDTO.getId()));
		Optional<Movie> objMovie = movieRepository.findById(reviewDTO.getMovieId());
		Movie entityMovie = objMovie.orElseThrow(() -> new ResourceNotFoundException("Error. Movie Id not found: "));
		Review entityReview = modelMapper.map(reviewDTO, Review.class);
		entityReview.setMovie(entityMovie);
		entityReview.setUser(entityUser);

		entityReview = reviewRepository.save(entityReview); // reposity.save() returns a reference to object saved in DB
		return new ReviewDTO(entityReview.getId(),
							 entityReview.getText(),
							 entityReview.getMovie().getId(),
							 entityReview.getUser());
	}


}
