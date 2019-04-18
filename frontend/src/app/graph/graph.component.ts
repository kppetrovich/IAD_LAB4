import { Component, OnInit } from '@angular/core';
import {Dot} from "./Dot";
@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.css']
})
export class GraphComponent implements OnInit {

  constructor() { }
  x: number;
  y: number;
  r: number;
  ngOnInit() {

  }
  getCoord(event) {
    const dim = document.querySelector('svg').getBoundingClientRect();
    const x = event.clientX - dim.left;
    const y = event.clientY - dim.top;

  }

}
