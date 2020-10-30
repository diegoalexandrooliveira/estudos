import { InMemoryDbService, RequestInfo } from 'angular-in-memory-web-api';
import { Observable } from 'rxjs';


export class InMemoryService implements InMemoryDbService {

  createDb(reqInfo?: RequestInfo): {} | Observable<{}> | Promise<{}> {
    let pessoas = [{
      id: 1,
      nome: 'Diego'
    }, {
      id: 2,
      nome: 'João'
    }];

    return { pessoas };
  }

}
