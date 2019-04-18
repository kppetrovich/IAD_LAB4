import { Component, OnInit } from '@angular/core';
import {Dot} from "./Dot";
import {AuthService} from "../auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ApiService} from "../api.service";
@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.css'],
  providers: [ApiService]
})
export class GraphComponent implements OnInit {

  constructor(
    private _router: Router,
    private server: ApiService,
    private route: ActivatedRoute,
    private auth: AuthService,
  ) {
  }
  x: number;
  y: number;
  r: number;
  model = new Dot(this.x, this.y, this.r, false);
  dotsCollection: Dot[];
  submitted = false;
  isError = false;
  isNaN: Function = Number.isNaN;
  round: Function = Math.round;
  ngOnInit() {
    this.server.getAllDots().subscribe(dots => {
        this.dotsCollection = dots;
        this.isError = false;
      },
      error => this.isError = true);
  }
  getCoord(event) {
    const dim = document.querySelector('svg').getBoundingClientRect();
    const x = event.clientX - dim.left;
    const y = event.clientY - dim.top;
    let dot = new Dot(Number(((x - 125) / (100 / Number(this.model.r))).toFixed(3)), Number(((Number(y) - 125) / ((-1) * 100 / Number(this.model.r))).toFixed(3)), (this.model.r), false);
    this.addDot(dot);
  }
  deleteDots() {
    this.server.deleteAllDots().subscribe(ok => {
      this.isError = false;
      this.dotsCollection = [];
    }, error => this.isError = true);
  }
  onBack() {
    this.auth.doSignOut();
    this.server.logout().subscribe();
    this._router.navigate(['/']);
  }
  addDot(dot: Dot) {
    this.server.addDot(dot).subscribe(d => {
        this.dotsCollection.push(d);
        this.isError = false;
      },
      error => this.isError = true);
  }

}
