import { RouterModule, Routes} from '@angular/router';
import { NgModule } from '@angular/core';
import {RegisterComponent} from './registration/register.component';
import {LoginComponent} from './login/login.component';


const authRoutes : Routes =  [
    {
        path:'',
        
        children:[
            {
                path:'',
                redirectTo:'/login',
                pathMatch:'full',

            },
            {
                path :'register',
                component : RegisterComponent

            },
            {
                path :'login',
                component : LoginComponent

            }

        ] 
    }

];

@NgModule({
    imports: [
        RouterModule.forChild(authRoutes),
    ],
    exports:[
        RouterModule,
    ],
    
  })
export class AuthenticationRouterModule {}


