import { Component, OnInit } from '@angular/core';
import { CarregamentoService, Eventos } from './carregamento.service';

@Component({
  selector: 'app-carregamento',
  templateUrl: './carregamento.component.html',
  styleUrls: ['./carregamento.component.css']
})
export class CarregamentoComponent implements OnInit {

  exibir: boolean = false;

  constructor(private eventosCarregamento: CarregamentoService) { }

  ngOnInit(): void {
    this.eventosCarregamento.escutar(Eventos.HTTP_REQUEST)
      .subscribe(() => this.exibir = true);

    this.eventosCarregamento.escutar(Eventos.HTTP_RESPONSE)
      .subscribe(() => this.exibir = false);
  }

}
