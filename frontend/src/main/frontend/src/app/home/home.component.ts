import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormGroup, FormControl, FormArray} from '@angular/forms';
import {GeneticService} from '../services/genetic.service';
import {Gene} from '../models/gene';
import {FunctionRequest} from '../models/functionRequest';
import {GeneticRequest} from '../models/geneticRequest';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {

  myForm:FormGroup;
  public functionRequest:FunctionRequest = {
    maxRange: [7],
    minRange: [0],
    varsNumber: 1,
  };

  public geneticRequest:GeneticRequest = {
    functionRequest: this.functionRequest,
    numberOfGenerations: 100,
    uniformRate: 0.8,
    mutationRate: 0.1,
    tournamentSize: 5,
    elitism: true,
    populationSize: 50
  };


  x:number[] = [];
  y:number[] = [];

  fittest:Gene;
  xPoints:number[] = [];
  yPoints:number[] = [];
  xMax:number[] = [];
  yMax:number[] = [];
  subscriptions = [];

  public data = this.initData();
  public layout = {width: 1000, height: 500, title: 'Plot'};

  constructor(private geneticService:GeneticService) {
  }

  ngOnInit() {
    this.myForm = new FormGroup({
      functionRequest: new FormControl(),
      numberOfGenerations: new FormControl(),
      uniformRate: new FormControl(),
      mutationRate: new FormControl(),
      tournamentSize: new FormControl(),
      elitism: new FormControl(),
      populationSize: new FormControl()
    });
    console.log(this.myForm);
    this.myForm.setValue(this.geneticRequest);

    this.getFunction();
    this.getGenetic();
  }

  getFunction() {
    this.subscriptions.push(this.geneticService.getFunction(this.functionRequest).subscribe(
        resp => {
        resp.map(gene => {
          this.x.push(gene.vars[0]);
          this.y.push(gene.fitness);
        });
      },
        error => console.log(error)
    ));
  }

  getGenetic() {
    this.cleanGraph();
    this.subscriptions.push(this.geneticService.getGenetic(this.geneticRequest).subscribe(
        resp => {
        this.fittest = resp.fittest;
        this.xMax.push(this.fittest.vars[0]);
        this.yMax.push(this.fittest.fitness);
        resp.population.fittest.genes.forEach(gene => {
          this.xPoints.push(gene.vars[0]);
          this.yPoints.push(gene.fitness);
        });
      }
    ))
  }

  cleanGraph() {
    this.xMax = [];
    this.yMax = [];
    this.xPoints = [];
    this.yPoints = [];
    this.data = this.initData();
  }

  initData() {
    return [
      {x: this.x, y: this.y, type: 'scatter', mode: 'lines+points', marker: {color: 'blue'}, name: 'Function'},
      {
        x: this.xPoints,
        y: this.yPoints,
        mode: 'markers',
        marker: {
          color: 'green'
        },
        name: 'Fittest population'
      },
      {
        x: this.xMax,
        y: this.yMax,
        mode: 'points',
        marker: {
          color: 'red'
        },
        name: 'Fittest gene'
      }];
  }

  startGenetic() {
    this.geneticRequest = this.myForm.getRawValue();
    console.log(this.geneticRequest);
    this.getGenetic();
  }

  ngOnDestroy():void {
    this.subscriptions.forEach(s => s.unsubscribe());
  }

}
