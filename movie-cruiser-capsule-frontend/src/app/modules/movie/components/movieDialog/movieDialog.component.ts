
import { Component, OnInit, Inject } from '@angular/core';
import { Movie } from '../../movie';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MovieService } from '../../movie.service';

@Component({
  selector: 'movie-movie-dialog',
  templateUrl: './movieDialog.component.html',
  styleUrls: ['./movieDialog.component.css']
})
export class MovieDialogComponent implements OnInit {
  movie: Movie;
  comments: string;
  actionType: string;

  constructor(private snackBar: MatSnackBar,public dialogRef: MatDialogRef<MovieDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private movieService: MovieService, ) {
      this.comments = data.obj.comments;
      this.movie = data.obj;
    
    this.actionType = data.actionType;
  }

  ngOnInit() {
    console.log(this.data);
  }

  onCancel() {
    this.dialogRef.close();
  }

  updateOverview() {
    console.log("comments" + this.comments);
    this.movie.overview = this.comments;
    this.dialogRef.close();
    this.movieService.updateWatchListMovie(this.movie).subscribe((movie) => {
      this.snackBar.open('Movie updated in watch list', '', {
        duration: 2000,
      });

    });

  }

}