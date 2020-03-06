import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule,Routes } from '@angular/router';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpClient } from 'selenium-webdriver/http';
import { HttpClientModule } from '@angular/common/http';
import { MovieService } from './movie.service';
import { PopularComponent } from './components/popular/popular.component';
import { TopRatedComponent } from './components/top-rated/top-rated.component';
import { ContainerComponent } from './components/container/container.component';
import { MovieRouterModule } from './movie-router.module';
import { TokenInterceptor } from './interceptor.service';
import { MatCardModule } from '@angular/material/card';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { SearchComponent } from './components/search/search.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MovieDialogComponent } from './components/movieDialog/movieDialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';


@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    MatCardModule,
    MatSnackBarModule,
    FormsModule,
    MatInputModule,
    MovieRouterModule,

  ],
  exports:[
    ThumbnailComponent,
    ContainerComponent,
    SearchComponent,
    MatCardModule,
    TmdbContainerComponent,
    MovieDialogComponent,
    FormsModule,
    MatInputModule,
    MovieRouterModule,
  ],
  declarations: [ThumbnailComponent, PopularComponent, WatchlistComponent, ContainerComponent,SearchComponent, TmdbContainerComponent, MovieDialogComponent],
  entryComponents:[MovieDialogComponent],
  
  //exports:[ThumbnailComponent, PopularComponent, WatchlistComponent, ContainerComponent, TmdbContainerComponent, MovieDialogComponent],
  providers: [MovieService,{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  }]
})
export class MovieModule { }
