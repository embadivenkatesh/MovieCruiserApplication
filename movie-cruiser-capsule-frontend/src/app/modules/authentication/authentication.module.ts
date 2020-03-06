import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { HttpClientModule } from '@angular/common/http';

import {MatCardModule} from '@angular/material/card';
import {MatSnackBarModule} from '@angular/material/snack-bar';

//import { MovieRouterModule } from './movie-router.module';
import {FormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import { RegisterComponent } from './registration/register.component';
import {MatFormFieldModule, MatIcon} from '@angular/material';
import {MatButtonModule} from '@angular/material/button'; 
import { AuthenticationService } from '../authentication/authentication.service';
import { AuthenticationRouterModule } from '../authentication/authentication-router.module';


@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    MatCardModule,
    MatSnackBarModule,
    FormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    AuthenticationRouterModule
  ],
  exports:[
    MatCardModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    LoginComponent, RegisterComponent
  ],
  declarations: [LoginComponent, RegisterComponent],
  providers: [AuthenticationService],
  
})
export class AuthenticationModule { }
