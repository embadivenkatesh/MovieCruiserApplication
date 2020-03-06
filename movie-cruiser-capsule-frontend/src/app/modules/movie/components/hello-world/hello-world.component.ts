import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'movie-hello-world',
  //templateUrl: './hello-world.component.html',
  template:
  'Hello world works',
  styleUrls: ['./hello-world.component.css']
})
export class HelloWorldComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
