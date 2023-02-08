package com.devsuperior.movieflix.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.MovieGenreDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired 
	private ReviewRepository reviewRepository;



	@Autowired
	private ModelMapper modelMapper;

	@Transactional(readOnly = true)
	public MovieGenreDTO findById(Long id) {
		Optional<Movie> obj = movieRepository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Error. Id not found: " + id));
		GenreDTO genreDTO = modelMapper.map(entity.getGenre(), GenreDTO.class);
		MovieGenreDTO movieDTO = modelMapper.map(entity, MovieGenreDTO.class);
		movieDTO.setGenre(genreDTO);

		return movieDTO;
	}

	// ACID properties
	@Transactional(readOnly = true)
	public Page<MovieGenreDTO> findAllPaged(Long genreId, Pageable pageRequest) {
		List<Genre> genres = (genreId == 0) ? null : Arrays.asList(genreRepository.getOne(genreId));
		Page<Movie> page = movieRepository.findProductsWithFilter(genres, pageRequest);
		return page.map(p -> {
			MovieGenreDTO movieGenreDTO = modelMapper.map(p, MovieGenreDTO.class);
			movieGenreDTO.setGenre(modelMapper.map(p.getGenre(), GenreDTO.class));
			return movieGenreDTO;
		});

	}

	public List<ReviewDTO> findAllReviews(Long movieId) {
 		List<Review> listReviews = reviewRepository.findReviewsByMovieId(movieId);
        // precisa entrar UserDTO e movieId
		return listReviews.stream().map(x -> new ReviewDTO(x.getId(),
													       x.getText(),
													       x.getMovie().getId(),
													       x.getUser()))
														   .collect(Collectors.toList());
	}

}
