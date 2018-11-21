import {Component, OnDestroy, OnInit} from '@angular/core';
import {GeneticService} from '../services/genetic.service';
import {Gene} from '../models/gene';
import {FormGroup, FormControl, FormArray} from '@angular/forms';
import {FunctionRequest} from '../models/functionRequest';
import {GeneticRequest} from '../models/geneticRequest';

@Component({
  selector: 'app-second',
  templateUrl: './second.component.html',
  styleUrls: ['./second.component.css']
})
export class SecondComponent implements OnInit, OnDestroy {

  myForm:FormGroup;

  public functionRequest:FunctionRequest = {
    maxRange: [100, 100],
    minRange: [-100, -100],
    varsNumber: 2,
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
  z:number[] = [];

  fittest:Gene;
  xPoints:number[] = [];
  yPoints:number[] = [];
  zPoints:number[] = [];
  xMax:number[] = [];
  yMax:number[] = [];
  zMax:number[] = [];


  subscriptions = [];

  public data = this.initData();
  public layout = {
    margin: {
      l: 0,
      r: 0,
      b: 0,
      t: 0
    }
  };

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
          this.y.push(gene.vars[1]);
          this.z.push(gene.fitness);
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
        this.yMax.push(this.fittest.vars[1]);
        this.zMax.push(this.fittest.fitness);
        resp.population.fittest.genes.forEach(gene => {
          this.xPoints.push(gene.vars[0]);
          this.yPoints.push(gene.vars[1]);
          this.zPoints.push(gene.fitness);
        });
      }
    ));
  }

  cleanGraph() {
    this.xMax = [];
    this.yMax = [];
    this.zMax = [];
    this.xPoints = [];
    this.yPoints = [];
    this.zPoints = [];
    this.data = this.initData();
  }

  startGenetic() {
    this.geneticRequest = this.myForm.getRawValue();
    console.log(this.geneticRequest);
    this.getGenetic();
  }

  initData() {
    return [
      {
        x: this.x,
        y: this.y,
        z: this.z,
        mode: 'lines',
        marker: {
          color: '#1f77b4',
          size: 12,
          symbol: 'circle',
          line: {
            color: 'rgb(0,0,0)',
            width: 0
          }
        },
        line: {
          color: '#1f77b4',
          width: 1
        },
        type: 'scatter3d'
      },
      {
        x: this.xPoints,
        y: this.yPoints,
        z: this.zPoints,
        mode: 'markers',
        marker: {
          color: 'green',
          size: 5,
          symbol: 'circle',
          line: {
            color: 'rgb(0,0,0)',
            width: 0
          }
        },
        name: 'Fittest population',
        type: 'scatter3d'
      },
      {
        x: this.xMax,
        y: this.yMax,
        z: this.zMax,
        mode: 'points',
        marker: {
          color: 'red',
          size: 10,
          symbol: 'circle',
          line: {
            color: 'rgb(0,0,0)',
            width: 0
          }
        },
        name: 'Fittest gene',
        type: 'scatter3d'
      },
    ];
  }

  ngOnDestroy():void {
    this.subscriptions.forEach(s => s.unsubscribe());
  }
}
