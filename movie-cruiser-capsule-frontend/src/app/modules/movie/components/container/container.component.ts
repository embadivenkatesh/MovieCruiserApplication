import { Component, OnInit ,Input} from '@angular/core';
 import { Movie } from '../../movie';
// import { MovieService } from '../../movie.service';
// import { ActivatedRoute } from '@angular/router';

import { MovieService } from '../../movie.service';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';



@Component({
  selector: 'movie-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {
  
  @Input()
  movies: Array<Movie>;

  @Input()
  useWatchListApi:boolean;
  
  constructor(private movieService: MovieService, private snackBar: MatSnackBar) {
    this.movies = [];

  }

  ngOnInit() {

  }

  addToWatchlist(movie) {
    this.movieService.addMoviesTowatchList(movie).subscribe(movie => console.log(movie));
    this.snackBar.open('Movie added to watch list', '', {
      duration: 1000,
    });

  }

  deleteWatchListMovie(movie) {
    for (let i = 0; i < this.movies.length; i++) {
      if (movie.title == this.movies[i].title) {
        this.movies.splice(i, 1);
      }

    }
    this.movieService.deleteWatchListMovie(movie).subscribe(movie => {
      this.snackBar.open('Movie deleted from watch list', '', {
        duration: 500,
      });
    });
  }
  

  }
