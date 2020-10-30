import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'lib-shared-library',
  template: `
    <p>
      shared-library works! Really works
    </p>
  `,
  styles: [
  ]
})
export class SharedLibraryComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
