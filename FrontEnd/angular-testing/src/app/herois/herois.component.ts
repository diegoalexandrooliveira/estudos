import { Component, OnInit } from '@angular/core';
import { Heroi } from '../heroi';
import { HeroiService } from '../heroi.service';

@Component({
  selector: 'app-herois',
  templateUrl: './herois.component.html',
  styleUrls: ['./herois.component.css']
})
export class HeroisComponent implements OnInit {

  herois: Heroi[];

  constructor(private heroiService: HeroiService) { }

  ngOnInit(): void {
    this.recuperarHerois();
  }

  recuperarHerois() {
    this.heroiService.getHerois().subscribe(herois => this.herois = herois);
  }

  adicionar(nome: string) {
    const nomeSemEspaco = nome.trim();
    if (!nome) {
      return;
    }

    const forca = 11;

    this.heroiService.adicionaHeroi({ nome: nomeSemEspaco, forca: forca })
      .subscribe(heroi => this.herois.push(heroi));
  }

  excluir(heroi: Heroi) {
    this.herois = this.herois.filter(h => h !== heroi);
    this.heroiService.excluirHeroi(heroi).subscribe();
  }

}
