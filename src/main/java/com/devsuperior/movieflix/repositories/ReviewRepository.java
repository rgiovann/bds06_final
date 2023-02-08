package com.devsuperior.movieflix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

//	@Query("SELECT DISTINCT obj FROM Movie obj INNER JOIN obj.genre gen "
//		  	 + "WHERE (:genres IS NULL OR gen IN :genres) "
//		  	 + "ORDER by obj.title")
//	Page<Movie> findProductsWithFilter(List<Genre> genres, Pageable page);
	
    @Query("SELECT obj FROM Review obj WHERE obj.movie.id = :movieId")
    List<Review> findReviewsByMovieId(  Long movieId);
}
