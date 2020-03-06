package com.stackroute.moviecruiserbackend.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.moviecruiserbackend.domain.Movie;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Transactional
public class MovieRepositoryTest {

	@Autowired
	private MovieRepository movieRepository;

	@Test
	public void testSaveMovie() throws Exception {
		movieRepository.save(new Movie(1, "hello", "good movie", "www.ddd.com", "2012-03-23", 7.5, 3, "123", "venkat"));
		Movie movie = movieRepository.getOne(1);
		assertThat(movie.getId()).isEqualTo(1);

	}

	@Test
	public void testUpdateMovie() throws Exception {
		movieRepository.save(new Movie(1, "hello", "good movie", "www.ddd.com", "2012-03-23", 7.5, 3,"123", "venkat"));
		Movie fetchedMovie = movieRepository.getOne(1);
		assertEquals(fetchedMovie.getOverview(), "good movie");
		fetchedMovie.setOverview("bad movie");
		Movie updatedMovie = movieRepository.save(fetchedMovie);
		assertEquals(updatedMovie.getOverview(), "bad movie");

	}

	@Test
	public void testDeleteMovie() throws Exception {
		movieRepository.save(new Movie(1, "hello", "good movie", "www.ddd.com", "2012-03-23", 7.5, 3, "123", "venkat"));
		Movie movie = movieRepository.getOne(1);
		assertEquals(movie.getTitle(), "hello");
		movieRepository.delete(movie);
		assertEquals(Optional.empty(), movieRepository.findById(1));

	}

	@Test
	public void testGetMovie() throws Exception {
		movieRepository.save(new Movie(1, "hello", "good movie", "www.ddd.com", "2012-03-23", 7.5, 3, "123", "venkat"));
		Movie movie = movieRepository.getOne(1);
		assertEquals(movie.getTitle(), "hello");

	}

	@Test
	public void testGetAllMovies() throws Exception {
		movieRepository.save(new Movie(1, "hello", "good movie", "www.ddd.com", "2012-03-23", 7.5, 3, "123", "venkat"));
		movieRepository.save(new Movie(2, "spider", "good movie", "www.ddd.com", "2012-03-23", 7.5, 3, "1234", "prud"));
		List<Movie> movies = movieRepository.findAll();
		assertEquals(movies.get(0).getTitle(), "hello");
		assertEquals(movies.get(1).getTitle(), "spider");

	}
	
	@Test
	public void testFindByUserId() throws Exception {
		movieRepository.save(new Movie(3, "hello", "good movie", "www.ddd.com", "2012-03-23", 7.5, 3, "123", "first"));
		movieRepository.save(new Movie(4, "spider", "good movie", "www.ddd.com", "2012-03-23", 7.5, 3, "1234", "second"));		
		List<Movie> movies = movieRepository.findByUserId("second");
		assertEquals(movies.get(0).getTitle(), "spider");	

	}

}