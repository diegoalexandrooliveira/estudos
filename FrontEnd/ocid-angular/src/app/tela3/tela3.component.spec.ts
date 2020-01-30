import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Tela3Component } from './tela3.component';

describe('Tela3Component', () => {
  let component: Tela3Component;
  let fixture: ComponentFixture<Tela3Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Tela3Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Tela3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
