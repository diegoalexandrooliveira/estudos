import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class MensagemService {
  mensagens: string[] = [];

  adicionar(mensagem: string) {
    this.mensagens.push(mensagem);
  }

  limpar() {
    this.mensagens = [];
  }

}
