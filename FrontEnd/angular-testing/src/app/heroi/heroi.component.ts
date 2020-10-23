import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Heroi } from '../heroi';

@Component({
  selector: 'app-heroi',
  templateUrl: './heroi.component.html',
  styleUrls: ['./heroi.component.css']
})
export class HeroiComponent {

  @Input() heroi: Heroi;
  @Output() excluir = new EventEmitter();

  onExcluirClick($event) {
    $event.stopPropagation();
    this.excluir.next();
  }

}
