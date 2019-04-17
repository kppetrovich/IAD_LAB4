import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {GraphComponent} from "./graph/graph.component";
import {LoginComponent} from "./login/login.component";

const routes : Routes = [
  { path: 'graph', component: GraphComponent, data: {
      page: 'login'
    }},
  { path: 'login', component: LoginComponent, data: {
      page: 'graph'
    }},
  {path: '**', redirectTo: '/login'},
]

@NgModule({
  exports: [ RouterModule ],
  imports: [ RouterModule.forRoot(routes) ],
})

export class AppRoutingModule {}
