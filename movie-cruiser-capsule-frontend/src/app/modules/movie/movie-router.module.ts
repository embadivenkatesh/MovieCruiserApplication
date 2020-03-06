import { NgModule } from '@angular/core';
import { RouterModule,Routes } from '@angular/router';
import { PopularComponent } from './components/popular/popular.component';
import { TopRatedComponent } from './components/top-rated/top-rated.component';
import { Container } from '@angular/compiler/src/i18n/i18n_ast';
import { ContainerComponent } from './components/container/container.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { SearchComponent } from './components/search/search.component';
import { AuthGuardService } from '../authGuard.service';

const movieRoutes: Routes = [
    {
        path: 'movies',
        children: [
            {
            path: 'top_rated',
            component: TmdbContainerComponent,
            }
                

        //     {
        //         path: '',
        //         redirectTo:'/movies/popular',
        //         pathMatch:'full',
        //         //canActivate: [AuthGuardService]
        //     },
        //     {
        //         path: 'popular',
        //         component: TmdbContainerComponent,
        //         //canActivate: [AuthGuardService],
        //         data: {
        //             movieType: 'popular'
        //         } ,
        //     },
        //     {
        //         path: 'top_rated',
        //         component: TmdbContainerComponent,
        //         //canActivate: [AuthGuardService],
        //         data: {
        //             movieType: 'top_rated'
        //         } 
        //     },
        //     {
        //     path: 'watchlist',
        //     component: WatchlistComponent,
        //     //canActivate: [AuthGuardService]
                 
        //     },
        //     {
        //         path: 'search',
        //         component: SearchComponent,
        //        // canActivate: [AuthGuardService]
                     
        //     }
         ]
    }
];
@NgModule({
    imports: [
RouterModule.forChild(movieRoutes),
    ],
    exports: [
        RouterModule
    ]
})
export class MovieRouterModule{ }