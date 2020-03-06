import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Movie } from './movie';
import { map } from 'rxjs/operators/map';
import { Observable }from 'rxjs/Observable';
import { retry }from 'rxjs/operators';

@Injectable()
export class MovieService {
  imagePrefix: string 
  tmdbEndpoint: string ;
  apiKey:string;
  watchListEndpoint:string;
  springEndpoint:string;
  searchUrl:string;

   constructor(private httpClient: HttpClient) {
     this.apiKey= "api_key=ec4b04627bf3dc5f4963218f0d0ad467";
    this.tmdbEndpoint = "https://api.themoviedb.org/3/movie";
    this.imagePrefix = "https://image.tmdb.org/t/p/w500";
    this.watchListEndpoint= "http://localhost:3000/watchlist";
    this.springEndpoint="http://localhost:8082/api/movie";
    this.searchUrl="https://api.themoviedb.org/3/search/movie";
  }
  
  getMovies(type: string, page: number = 1): Observable<Array<Movie>>{
    const endpoint= `${this.tmdbEndpoint}/${type}?${this.apiKey}&page=${page}`;
   return this.httpClient.get(endpoint).pipe(
    map(this.pickMovieResults),
map(this.transforPosterPath.bind(this)));

  }
  



transforPosterPath(movies):Array<Movie>{
  return movies.map(movie => {
    movie.poster_path = `${this.imagePrefix}${movie.poster_path}`;
    return movie;
  });

}
pickMovieResults(response) {
  return response['results'];
}


 addMoviesTowatchList(movie){
  return this.httpClient.post(this.springEndpoint,movie);
}
 getWatchListedMovies():Observable<Array<Movie>> {
  const getMyMovies=`${this.springEndpoint}/movies`;
   return this.httpClient.get<Array<Movie>>(getMyMovies);
 }


 deleteWatchListMovie(movie){
  const deleteUrl=`${this.springEndpoint}/${movie.id}`;
  return this.httpClient.delete(deleteUrl, {responseType:'text'});
}

updateWatchListMovie(movie){
  const putUrl=`${this.springEndpoint}/${movie.id}`;
  return this.httpClient.put(putUrl, movie);
}

getSearchMovies(searchkey): Observable<Array<Movie>>{
  const url =`${this.searchUrl}?${this.apiKey}&language=en-US&page=1&include_adult=false&query=${searchkey}`;
  return this.httpClient.get(url).pipe(
    map(this.getMoviesFromResponse),
  map(this.prefixMovieImageInMovies.bind(this)));
    }

    getMoviesFromResponse(response) {
      return response['results'];
  
    }

    prefixMovieImageInMovies(movies):Array<Movie>{

      return movies.map(movie => {
        movie.poster_path = `${this.imagePrefix}${movie.poster_path}`;
        return movie;
      });
    }

}