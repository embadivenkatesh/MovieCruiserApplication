package com.stackroute.moviecruiserbackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.moviecruiserbackend.domain.Movie;
import com.stackroute.moviecruiserbackend.exception.MovieExistException;
import com.stackroute.moviecruiserbackend.exception.MovieNotFoundException;
import com.stackroute.moviecruiserbackend.repository.MovieRepository;

@Component
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieDao;

	@Override
	public boolean saveMovie(Movie movie) throws MovieExistException {
		final Optional<Movie> object = movieDao.findById(movie.getId());
		if (object.isPresent()) {
			throw new MovieExistException("Movie is already exist");
		}

		movieDao.save(movie);
		return true;
	}

	@Override
	public Movie updateMovie(Movie movie) throws MovieNotFoundException {
		Movie movieObj = movieDao.findById(movie.getId()).orElse(null);
		if (movieObj == null) {
			throw new MovieNotFoundException("Movie Not Found");
		}
		movieObj.setOverview(movie.getOverview());
		movieDao.save(movie);
		return movieObj;
	}

	@Override
	public boolean deleteMovieByid(int id) throws MovieNotFoundException {
		Movie movieObj = movieDao.findById(id).orElse(null);
		if (movieObj == null) {
			throw new MovieNotFoundException("Movie Not Found");
		}
		movieDao.delete(movieObj);
		return true;
	}

	@Override
	public Movie getMovieByid(int id) throws MovieNotFoundException {
		Movie movieObj = movieDao.findById(id).orElse(null);
		if (movieObj == null) {
			throw new MovieNotFoundException("Movie Not Found");
		}
		return movieObj;
	}

	@Override
	public List<Movie> getMovies() {
		return movieDao.findAll();

	}

	@Override
	public List<Movie> getMyMovies(String userId) {
		return movieDao.findByUserId(userId);
	}

}
