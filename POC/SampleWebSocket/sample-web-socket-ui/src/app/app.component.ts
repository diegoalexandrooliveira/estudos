import { Component } from '@angular/core';
import * as Stomp from 'stompjs';

import { WebSocketConnector } from './WebSocketConnector/web-socket-connector';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  public messages: string[] = [];

  constructor() {
    const ws = new WebSocketConnector("http://localhost:8080/socket", "statusApp", this.onMessage.bind(this));
  }

  onMessage(message: Stomp.Message): void {
    this.messages.push(message.body);
  }
}
