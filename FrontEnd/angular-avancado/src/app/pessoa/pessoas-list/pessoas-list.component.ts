import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Pessoa } from '../pessoa.model';
import { PessoasService } from '../pessoas.service';

@Component({
  selector: 'app-pessoas-list',
  templateUrl: './pessoas-list.component.html',
  styleUrls: ['./pessoas-list.component.css']
})
export class PessoasListComponent implements OnInit {

  pessoas$: Observable<Pessoa[]>;

  constructor(private pessoaService: PessoasService) {
    this.pessoas$ = this.pessoaService.get();
  }

  ngOnInit(): void {
  }


}
