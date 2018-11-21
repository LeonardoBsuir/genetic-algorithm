import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {GeneticResponse} from '../models/geneticResponse';
import {Gene} from '../models/gene';
import {FunctionRequest} from '../models/functionRequest';
import {GeneticRequest} from '../models/geneticRequest';

@Injectable()
export class GeneticService {

  private GENETIC_URL = '/api/genetic';

  private FUNCTION_URL = '/api/function';

  constructor(private httpClient: HttpClient) {
  }

  public getGenetic(geneticRequest: GeneticRequest): Observable<GeneticResponse> {
    return this.httpClient.post<GeneticResponse>(this.GENETIC_URL, geneticRequest);
  }

  public getFunction(functionRequest: FunctionRequest): Observable<Gene[]> {
    return this.httpClient.post<Gene[]>(this.FUNCTION_URL, functionRequest);
  }

}
