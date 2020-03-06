import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MovieModule } from './modules/movie/movie.module'
import { AuthenticationModule } from './modules/authentication/authentication.module'
import { RouterModule,Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule,MatToolbar } from '@angular/material/toolbar';
import {MatDialogModule} from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { AuthGuardService } from '../app/modules/authGuard.service';
import {MatInputModule} from '@angular/material/input';
import { ContainerComponent } from './modules/movie/components/container/container.component';
import { TmdbContainerComponent } from './modules/movie/components/tmdb-container/tmdb-container.component';
import { WatchlistComponent } from './modules/movie/components/watchlist/watchlist.component';
import { SearchComponent } from './modules/movie/components/search/search.component';
import {MatButtonModule} from '@angular/material/button';
const appRoutes: Routes= [
  // {
  //   path: '',
  //   redirectTo: '/movies',
  //   pathMatch: 'full'

  // }
  {
      path: '',
      redirectTo: '/login',
      pathMatch: 'full'
  },
  {
    path: '',
    redirectTo:'popular',
    pathMatch:'full',
    
},
{
    path: 'popular',
    component: TmdbContainerComponent,
    canActivate: [AuthGuardService],
    data: {
        movieType: 'popular'
    } ,
},
{
    path: 'top_rated',
    component: TmdbContainerComponent,
    canActivate: [AuthGuardService],
    data: {
        movieType: 'top_rated'
    } 
},
{
path: 'watchlist',
component: WatchlistComponent,
canActivate: [AuthGuardService],
},
{
    path: 'search',
    component: SearchComponent, 
    canActivate: [AuthGuardService],      
}
]

@NgModule({
  declarations: [
    AppComponent
    ],
  imports: [
    MovieModule,
    AuthenticationModule,
    BrowserModule,
    MatToolbarModule,
    MatDialogModule,
    FormsModule,
    MatInputModule,
    BrowserAnimationsModule,
    MatButtonModule,
    RouterModule.forRoot(appRoutes),
  ],
  providers: [AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }