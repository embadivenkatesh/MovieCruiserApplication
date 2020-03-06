import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Movie } from '../../movie';
import { HttpClient } from '@angular/common/http';
import { MovieService } from '../../movie.service';
import { MovieDialogComponent } from '../../components/movieDialog/movieDialog.component';

import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'movie-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {
  @Input()
  movie: Movie;

  @Input()
  useWatchListApi: boolean;

  @Output()
  addMovie = new EventEmitter();


  @Output()
  deleteMovie = new EventEmitter();

  @Output()
  updateMovie = new EventEmitter();

  constructor(private movieService: MovieService, private snackBar: MatSnackBar, private dialog: MatDialog) {

  } 
  

  ngOnInit() {
   

  }
  


   addToWatchlist() {
    this.addMovie.emit(this.movie);
  }
  
   deleteFromWatchList() {
    this.deleteMovie.emit(this.movie);
  }

  updateFromWatchList(action) {
    let dialogRef = this.dialog.open(MovieDialogComponent, {
       width: '400px',
       data: { obj: this.movie, action: action }
     });

   dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');

      });
   }


}


