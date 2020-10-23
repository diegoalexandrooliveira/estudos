import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from '../app-routing.module';
import { HeroiComponent } from './heroi.component';

describe('HeroiComponent (shallow)', () => {

  let fixture: ComponentFixture<HeroiComponent>;

  beforeEach(() => {

    TestBed.configureTestingModule({
      declarations: [HeroiComponent],
      schemas: [NO_ERRORS_SCHEMA]
    });

    fixture = TestBed.createComponent(HeroiComponent);

  });


  it('deve ter o heroi correto', () => {

    fixture.componentInstance.heroi = {
      forca: 20,
      nome: 'Wolverine',
      id: 10
    };

    expect(fixture.componentInstance.heroi.nome).toEqual('Wolverine');
  });


  it('deve desenhar o nome do heroi', () => {

    fixture.componentInstance.heroi = {
      forca: 20,
      nome: 'Wolverine',
      id: 10
    };

    fixture.detectChanges();

    expect(fixture.nativeElement.querySelector('a').textContent).toContain('Wolverine');
  });


});
