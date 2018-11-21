import {FunctionRequest} from './functionRequest';

export class GeneticRequest {

  functionRequest:FunctionRequest;

  numberOfGenerations:number;
  uniformRate:number;
  mutationRate:number;
  tournamentSize:number;
  elitism:boolean;
  populationSize:number;
}
