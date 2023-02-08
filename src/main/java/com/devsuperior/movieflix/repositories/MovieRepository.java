package com.devsuperior.movieflix.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query("SELECT DISTINCT obj FROM Movie obj INNER JOIN obj.genre gen "
		  	 + "WHERE (:genres IS NULL OR gen IN :genres) "
		  	 + "ORDER by obj.title")
	Page<Movie> findProductsWithFilter(List<Genre> genres, Pageable page);
	
//	@Query("SELECT obj FROM Movie obj JOIN FETCH obj.genres WHERE obj IN :movies")
//	List<Movie> findProductsWithCategories(List<Movie> movies);
}
