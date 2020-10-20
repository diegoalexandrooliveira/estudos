import { pipe } from 'rxjs';
import { ForcaPipe } from "./forca.pipe";


describe('ForcaPipe', () => {

  it('deve mostrar fraco se a força for 5', () => {
    const forcaPipe = new ForcaPipe();

    expect(forcaPipe.transform(5)).toEqual('5 (fraco)');
  });

  it('deve mostrar forte se a força for 10', () => {
    const forcaPipe = new ForcaPipe();

    expect(forcaPipe.transform(10)).toEqual('10 (forte)');
  });

  it('deve mostrar inacreditavel se a força for 30', () => {
    const forcaPipe = new ForcaPipe();

    expect(forcaPipe.transform(30)).toEqual('30 (inacreditavel)');
  });
});
