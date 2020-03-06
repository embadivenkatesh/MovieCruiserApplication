import { Component, OnInit } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'movie-watchlist',
  templateUrl: './watchlist.component.html',
  
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {

movies:Array<Movie>;
useWatchListApi = true;

  constructor(private http:HttpClient ,private movieServce: MovieService,private matSnackBar:MatSnackBar) { 
    this.movies=[];

  }

  ngOnInit() {
    let message=" Watch List is Empty";
    this.movieServce.getWatchListedMovies().subscribe((movies) => {
      if(movies.length===0)
      {
this.matSnackBar.open(message,'',{
  duration:1000
});
      }
 //console.log(movies);
      this.movies.push(...movies);
    })
  }

}
