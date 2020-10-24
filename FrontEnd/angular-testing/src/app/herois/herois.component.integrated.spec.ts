import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { By } from '@angular/platform-browser';
import { of } from 'rxjs';
import { Heroi } from '../heroi';
import { HeroiService } from '../heroi.service';
import { HeroisComponent } from './herois.component';


describe('HeroisComponent (integrated)', () => {

  let fixture: ComponentFixture<HeroisComponent>;
  let mockService;
  let HEROIS: Heroi[];

  beforeEach(() => {

    HEROIS = [
      {
        id: 1,
        nome: 'Homem aranha',
        forca: 10
      },
      {
        id: 2,
        nome: 'Batman',
        forca: 5
      }
    ];

    mockService = jasmine.createSpyObj(["getHerois", "adicionaHeroi", "excluirHeroi"]);

    TestBed.configureTestingModule({
      declarations: [HeroisComponent],
      providers: [{
        provide: HeroiService, useValue: mockService
      }],
      schemas: [NO_ERRORS_SCHEMA]
    });

    fixture = TestBed.createComponent(HeroisComponent);
  });

  it('deve buscar os herois', () => {

    mockService.getHerois.and.returnValue(
      of(HEROIS)
    );

    fixture.detectChanges();

    expect(fixture.componentInstance.herois.length).toEqual(2);
  });

  it('deve adicionar um heroi na lista', () => {
    mockService.getHerois.and.returnValue(
      of(HEROIS)
    );

    mockService.adicionaHeroi.and.callFake((heroi) => of({ ...heroi, id: 10 }));

    fixture.nativeElement.querySelector('input').value = 'Hulk';

    fixture.detectChanges();

    fixture.debugElement.query(By.css('button')).nativeElement.click();

    fixture.detectChanges();

    expect(fixture.componentInstance.herois.length).toEqual(3);
    expect(mockService.adicionaHeroi).toHaveBeenCalled();

  });


});
