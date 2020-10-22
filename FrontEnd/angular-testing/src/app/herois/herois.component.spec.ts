import { Heroi } from '../heroi';
import { HeroiService } from '../heroi.service';
import { HeroisComponent } from "./herois.component";
import { of } from 'rxjs';


describe('HeroisComponent', () => {

  let heroisComponent: HeroisComponent;
  let herois: Heroi[];
  let mockHeroiService;

  beforeEach(() => {
    herois = [
      {
        id: 1,
        forca: 8,
        nome: 'Spider-Man'
      },
      {
        id: 2,
        forca: 24,
        nome: 'Wonder Woman'
      },
      {
        id: 3,
        forca: 55,
        nome: 'Super-man'
      }
    ];

    mockHeroiService = jasmine.createSpyObj(['getHerois', 'adicionaHeroi', 'excluirHeroi']);
    // mockHeroiService.excluirHeroi.and.returnValue(of(true));

    mockHeroiService.excluirHeroi.and.callFake((heroi) => {
      console.log(heroi);
      return of(true);
    });

    heroisComponent = new HeroisComponent(mockHeroiService);
  });

  describe('deleção', () => {


    it('deve deletar um heroi', () => {
      heroisComponent.herois = herois;

      heroisComponent.excluir(herois[0]);

      expect(heroisComponent.herois).not.toContain(herois[0]);
      expect(heroisComponent.herois).toContain(herois[1]);
      expect(heroisComponent.herois).toContain(herois[2]);

    });
  });


});
