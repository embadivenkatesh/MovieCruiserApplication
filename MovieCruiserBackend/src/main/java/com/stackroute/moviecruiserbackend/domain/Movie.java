package com.stackroute.moviecruiserbackend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "movie_id")
	private String movieId;

	@Column(name = "name")
	private String title;

	@Column(name = "comments", length=800)
	private String overview;

	@Column(name = "poster_path")
	private String poster_path;

	@Column(name = "release_date")
	private String release_date;
	
	@Column(name = "rating")
	private double vote_average;
	
	@Column(name = "rating_count")
	private int vote_count;
	
	public Movie(int id, String movieId, String title, String overview, String poster_path, String release_date,
			double vote_average, int vote_count, String userId) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.title = title;
		this.overview = overview;
		this.poster_path = poster_path;
		this.release_date = release_date;
		this.vote_average = vote_average;
		this.vote_count = vote_count;
		this.userId = userId;
	}

	@Column(name = "userid")
	private String userId;
	
	public Movie(){
		
	}

	public Movie(int id, String name, String comments, String posterpath, String releasedate, double rating,
			int ratingcount, String movieId,String userId ) {
		super();
		this.id = id;
		this.title = name;
		this.overview = comments;
		this.poster_path = posterpath;
		this.release_date = releasedate;
		this.vote_average = rating;
		this.vote_count = ratingcount;
		this.movieId = movieId;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public double getVote_average() {
		return vote_average;
	}

	public void setVote_average(double vote_average) {
		this.vote_average = vote_average;
	}

	public int getVote_count() {
		return vote_count;
	}

	public void setVote_count(int vote_count) {
		this.vote_count = vote_count;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}	

}
