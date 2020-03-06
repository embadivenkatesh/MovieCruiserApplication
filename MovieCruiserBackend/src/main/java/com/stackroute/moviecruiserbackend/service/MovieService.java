package com.stackroute.moviecruiserbackend.service;

import java.util.List;

import com.stackroute.moviecruiserbackend.domain.Movie;
import com.stackroute.moviecruiserbackend.exception.MovieExistException;
import com.stackroute.moviecruiserbackend.exception.MovieNotFoundException;

public interface MovieService {

	boolean saveMovie(Movie movie) throws MovieExistException;

	Movie updateMovie(Movie movie) throws MovieNotFoundException;

	boolean deleteMovieByid(int id) throws MovieNotFoundException;

	Movie getMovieByid(int id) throws MovieNotFoundException;

	List<Movie> getMovies();
	
	List<Movie> getMyMovies(String userId);

}
