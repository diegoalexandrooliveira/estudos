import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Tela1Component } from './tela1.component';

describe('Tela1Component', () => {
  let component: Tela1Component;
  let fixture: ComponentFixture<Tela1Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Tela1Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Tela1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
