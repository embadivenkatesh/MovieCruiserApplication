package com.stackroute.moviecruiserbackend.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.moviecruiserbackend.domain.Movie;
import com.stackroute.moviecruiserbackend.exception.MovieExistException;
import com.stackroute.moviecruiserbackend.exception.MovieNotFoundException;
import com.stackroute.moviecruiserbackend.service.MovieService;

import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;

@RestController
@RequestMapping(path = "/api/movie")
@Api(value="movie management", description="Movie online management")
@CrossOrigin(origins ={"http://localhost:4200","http://localhost:49152" })
public class MovieController {

	@Autowired
	private MovieService movieService;

	@PostMapping//("/save")
	public ResponseEntity<?> saveMoview(@RequestBody Movie movie,HttpServletRequest request,HttpServletResponse response) {
		ResponseEntity<?> responseEntity;
		System.out.println("Movie is saving");
		String authHeader=request.getHeader("authorization");
		final String token=authHeader.substring(7);
		String userId=Jwts.parser().setSigningKey("secretekey").parseClaimsJws(token).getBody().getSubject();
		System.out.println("User id:"+userId);
		try {
			movie.setUserId(userId);
			movieService.saveMovie(movie);
			responseEntity = new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
		} catch (MovieExistException movieExistException) {
			responseEntity = new ResponseEntity<String>(movieExistException.getMessage(), HttpStatus.CONFLICT);
		}

		return responseEntity;

	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateMoview(@PathVariable String id, @RequestBody Movie movie) {
		ResponseEntity<?> responseEntity;
		try {
			movieService.updateMovie(movie);
			responseEntity = new ResponseEntity<Movie>(movie, HttpStatus.OK);
		} catch (MovieNotFoundException movieNotFoundException) {
			responseEntity = new ResponseEntity<String>(movieNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	@GetMapping
	public ResponseEntity<?> getAllMovies() {
		ResponseEntity<?> responseEntity;
		List<Movie> listMovies = movieService.getMovies();
		responseEntity = new ResponseEntity<List<Movie>>(listMovies, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteMovieById(@PathVariable Integer id) {
		ResponseEntity<?> responseEntity;
		try {
			movieService.deleteMovieByid(id);
			responseEntity = new ResponseEntity<String>("Movie deleted success", HttpStatus.OK);
		} catch (MovieNotFoundException movieNotFoundException) {
			responseEntity = new ResponseEntity<String>(movieNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
		}
		return responseEntity;

	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getAllMovieById(@PathVariable Integer id) {
		ResponseEntity<?> responseEntity;
		try {
			Movie listMovies = movieService.getMovieByid(id);
			responseEntity = new ResponseEntity<Movie>(listMovies, HttpStatus.OK);
		} catch (MovieNotFoundException movieNotFoundException) {
			responseEntity = new ResponseEntity<String>(movieNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	@GetMapping("/movies")
	public @ResponseBody ResponseEntity<List<Movie>> fetchMyMovies(final ServletRequest request,final ServletResponse response){
		
		HttpServletRequest httpReq = (HttpServletRequest)request;
		String authHeader= httpReq.getHeader("authorization");
		final String token=authHeader.substring(7);
		String userId=Jwts.parser().setSigningKey("secretekey").parseClaimsJws(token).getBody().getSubject();
		System.out.println("User id:"+userId);
		return new ResponseEntity<List<Movie>>(movieService.getMyMovies(userId),HttpStatus.OK);
	}

}
