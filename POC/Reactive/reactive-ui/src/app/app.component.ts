import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  public nomes: string[] = [];

  constructor(private httpClient: HttpClient, private cdr: ChangeDetectorRef) { }

  ngOnInit(): void {
    let eventSource = new EventSource('http://localhost:8080/api/cadastros/persistente');
    eventSource.onmessage = (event) => {
      this.nomes.push(JSON.parse(event.data)["nome"]);
      this.cdr.detectChanges();
    };
    eventSource.onerror = (error) => {
      if (eventSource.readyState === 0) {
        eventSource.close();
      }
    };
  }

}
