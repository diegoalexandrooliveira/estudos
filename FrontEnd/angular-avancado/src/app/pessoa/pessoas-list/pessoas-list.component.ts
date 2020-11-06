import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Pessoa } from '../pessoa.model';
import { PessoasService } from '../pessoas.service';

@Component({
  selector: 'app-pessoas-list',
  templateUrl: './pessoas-list.component.html',
  styleUrls: ['./pessoas-list.component.css']
})
export class PessoasListComponent implements OnInit {

  pessoas$: Observable<Pessoa[]>;

  nome: string;

  constructor(private pessoaService: PessoasService) {
    this.pessoas$ = this.pessoaService.get()
      .pipe(
        map(pessoas => {
          return pessoas.map(pessoa => { return { ...pessoa, novoID: this.transformaID_NoMAP(pessoa.id) } })
        })
      );
  }

  ngOnInit(): void {
  }

  // Função para demonstrar quantas vezes é chamada uma função ao usar no template
  // A cada caracter digitado no input, essa função é chamada para cada item do array
  // o angular não sabe o que mudou no input, logo ele precisa redesenhar a tela, chamando essa função toda vez
  transformaID(id: number): string {
    console.log('Chamou o transformaID');
    return `ID: ${id}`;
  }


  // Se chamar a função para cada item da lista no get, 
  // essa função só sera invocada uma vez para cada item
  private transformaID_NoMAP(id: number): string {
    console.log('Chamou o transformaID_NoMAP');
    return `Novo ID: ${id}`;
  }


}
