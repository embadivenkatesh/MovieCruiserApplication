package com.stackroute.moviecruiserbackend.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviecruiserbackend.domain.Movie;
import com.stackroute.moviecruiserbackend.service.MovieServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

	@MockBean
	private transient MovieServiceImpl movieService;

	@Autowired
	private transient MockMvc mockMvc;

	private transient static Movie movie;

	static List<Movie> movies;
	
	@InjectMocks
	private MovieController movieController;

	@Before
	public void setUp() {
		movies = new ArrayList<>();
		movie = new Movie(526645, "Rebel", "good", "test", "2018-08-08", 4.5, 5, "1001", "venkat");
		movies.add(movie);
		movie = new Movie(526645, "Rebel-1", "very-good", "test", "2018-08-08", 4.5, 5, "1001", "venkat");
		movies.add(movie);
	}

	@Test
	public void testSaveMovieSucess() throws JsonProcessingException, Exception {
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1MjY2NDUiLCJpYXQiOjE1NDMwNjcyMzl9.PcQtyWHNo5EoH-Te9UQnKHuxj8p0OnInBmKwLitsodI";
		Mockito.when(movieService.saveMovie(movie)).thenReturn(true);
		mockMvc.perform(post("/api/movie/save").header("Authorization","Bearer " + token).contentType(MediaType.APPLICATION_JSON).
				content(jsonToString(movie)))
				.andExpect(status().isCreated());
		verify(movieService, times(1)).saveMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(movieService);
	}

	@Test
	public void updateMovieSuccessFully() throws JsonProcessingException, Exception {
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1MjY2NDUiLCJpYXQiOjE1NDMwNjcyMzl9.PcQtyWHNo5EoH-Te9UQnKHuxj8p0OnInBmKwLitsodI";
		movie.setOverview("not good");
		Mockito.when(movieService.updateMovie(movie)).thenReturn(movies.get(0));
		mockMvc.perform(
				put("/api/movie/{id}", 101).header("Authorization","Bearer " + token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie)))
				.andExpect(status().isOk());
		verify(movieService, times(1)).updateMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(movieService);

	}

	@Test
	public void deleteMovieById() throws Exception {
		Mockito.when(movieService.deleteMovieByid(101)).thenReturn(true);
		mockMvc.perform(delete("/api/movie/{id}", 101)).andExpect(status().isOk());
		verify(movieService, times(1)).deleteMovieByid(101);
		verifyNoMoreInteractions(movieService);
	}

	@Test
	public void fetchMovieById() throws Exception {
		Mockito.when(movieService.getMovieByid(101)).thenReturn(movies.get(0));
		mockMvc.perform(get("/api/movie/{id}", 101)).andExpect(status().isOk());
		verify(movieService, times(1)).getMovieByid(101);
		verifyNoMoreInteractions(movieService);

	}

	@Test
	public void getAllMovies() throws Exception {
		Mockito.when(movieService.getMovies()).thenReturn(null);
		mockMvc.perform(get("/api/movie")).andExpect(status().isOk());
		verify(movieService, times(1)).getMovies();
		verifyNoMoreInteractions(movieService);

	}

	private static String jsonToString(final Object obj) throws JsonProcessingException {
		String results;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			String jsonContent = mapper.writeValueAsString(obj);
			results = jsonContent;
		} catch (JsonProcessingException exception) {
			results = "Json processing error";
		}
		return results;
	}

}
