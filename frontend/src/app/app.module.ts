import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import {SpinnerModule} from 'primeng/spinner';
import {InputTextModule} from 'primeng/inputtext';

import { AppComponent } from './app.component';
import {ButtonModule} from 'primeng/button';


import { AppRoutingModule } from './app-routing.module';
import { GraphComponent } from './graph/graph.component';
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    GraphComponent,
    LoginComponent,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    FormsModule,
    RouterModule,
    AppRoutingModule,
    SpinnerModule,
    InputTextModule,
    ButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
