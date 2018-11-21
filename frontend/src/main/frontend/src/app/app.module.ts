import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {SecondComponent} from './second/second.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing.module';
import {PlotlyModule} from 'angular-plotly.js';
import {HttpClientModule} from '@angular/common/http';
import {GeneticService} from './services/genetic.service';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SecondComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    PlotlyModule,
    HttpClientModule,
  ],
  providers: [GeneticService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
