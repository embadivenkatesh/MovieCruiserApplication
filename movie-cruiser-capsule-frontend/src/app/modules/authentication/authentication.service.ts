import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as jwt_decode from 'jwt-decode';
export const TOKEN_NAME:string='jwt_token';


@Injectable()
export class AuthenticationService {
     authEndpoint: string;
  constructor(private httpClient: HttpClient) {
    this.authEndpoint = "http://localhost:8085//api/V1/user";
   }

  registerUser(user){
    const endPoint=this.authEndpoint+"/register";
    return this.httpClient.post(endPoint, user, {responseType:'text'});
  }

  loginUser(user){
    const endPoint=this.authEndpoint+"/login";
    return this.httpClient.post(endPoint, user);
  }

  setToken(token:string){
    return localStorage.setItem(TOKEN_NAME,token);
  }

  getToken(){
    return localStorage.getItem(TOKEN_NAME);
  }
  deleteToken(){
    return localStorage.removeItem(TOKEN_NAME);
  }

  public isTokenExpired(token?: string): boolean {
    if (token === null || token === '') {
        return true;
    }
    if(!token) token=this.getToken();
    let date = this.getTokenExpirationDate(token);
    

    if (date === null) {
      return false;
    }

    return !(date.valueOf() > new Date().valueOf());
  }

  public getTokenExpirationDate(token: string ): Date | null {
    let decoded: any;
    decoded = jwt_decode(token);

    /* if (!decoded.hasOwnProperty('exp')) {
      return null;
    } */

    const date = new Date(0);
    date.setUTCSeconds(decoded.exp);

    return date;
  }
}
