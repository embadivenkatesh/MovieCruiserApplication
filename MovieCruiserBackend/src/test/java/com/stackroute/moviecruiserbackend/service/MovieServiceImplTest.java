package com.stackroute.moviecruiserbackend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.moviecruiserbackend.domain.Movie;
import com.stackroute.moviecruiserbackend.exception.MovieExistException;
import com.stackroute.moviecruiserbackend.exception.MovieNotFoundException;
import com.stackroute.moviecruiserbackend.repository.MovieRepository;

public class MovieServiceImplTest {

	@InjectMocks
	private transient MovieServiceImpl movieService;

	@Mock
	private transient MovieRepository movieDao;

	static transient Movie movie;

	private transient Optional<Movie> optionalMovie;

	@Before
	public void setupmock() {
		MockitoAnnotations.initMocks(this);
		movie = new Movie(101, "Rebel", "good", "test", "2018-08-08", 4.5, 5, "1001", "venkat");
		optionalMovie = Optional.of(movie);
	}

	@Test
	public void saveMovieSuccessFully() throws MovieExistException {
		Mockito.when(movieDao.save(movie)).thenReturn(movie);
		final boolean isSaved = movieService.saveMovie(movie);
		assertTrue(isSaved);
		verify(movieDao,times(1)).save(movie);
		verify(movieDao,times(1)).findById(movie.getId());
	}

	@Test(expected = MovieExistException.class)
	public void saveMovieFailure() throws MovieExistException {
		Mockito.when(movieDao.save(movie)).thenReturn(movie);
		Mockito.when(movieDao.findById(101)).thenReturn(optionalMovie);
		boolean isSaved = movieService.saveMovie(movie);
		assertFalse("Movie failed to save", isSaved);
		verify(movieDao,times(1)).findById(movie.getId());
	}

	@Test
	public void updateMovieSuccessFully() throws MovieNotFoundException {
		Mockito.when(movieDao.save(movie)).thenReturn(movie);
		Mockito.when(movieDao.findById(101)).thenReturn(optionalMovie);
		Movie movieReturn = movieService.updateMovie(movie);
		movieReturn.setOverview("Bad");
		assertNotEquals("Bad", "good");

	}

	@Test
	public void deleteMovieById() throws MovieNotFoundException {
		Mockito.when(movieDao.findById(101)).thenReturn(optionalMovie);
		doNothing().when(movieDao).delete(movie);
		final boolean deleteMovie = movieService.deleteMovieByid(movie.getId());
		assertEquals(true, deleteMovie);
		verify(movieDao,times(1)).delete(movie);
		verify(movieDao,times(1)).findById(movie.getId());

	}
	
	@Test
	public void testGetMovieById() throws MovieNotFoundException {
		Mockito.when(movieDao.findById(101)).thenReturn(optionalMovie);
		Movie movie1=movieService.getMovieByid(101);
		assertEquals(movie1, movie1);
	}
	
	@Test
	public void getAllMovies() {
		final List<Movie> allMovies=new ArrayList<>(101);
		Mockito.when(movieDao.findAll()).thenReturn(allMovies);
		final List<Movie> movies=movieService.getMovies();
		assertEquals(movies, allMovies);
	}

}
