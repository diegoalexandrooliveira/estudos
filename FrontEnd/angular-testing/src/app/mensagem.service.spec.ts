import { MensagemService } from "./mensagem.service";


describe('MensagemService', () => {

  let mensagemService: MensagemService;

  beforeEach(() => {
    mensagemService = new MensagemService();
  });


  it('não deve ter nenhuma mensagem ao iniciar', () => {
    expect(mensagemService.mensagens.length).toEqual(0);
  });

  it('deve adicionar uma mensagem ao chamar adicionar', () => {

    mensagemService.adicionar('mensagem');

    expect(mensagemService.mensagens.length).toEqual(1);
  });

  it('deve limpar as mensagens ao chamar limpar', () => {
    mensagemService.adicionar('mensagem');

    mensagemService.limpar();

    expect(mensagemService.mensagens.length).toEqual(0);
  });


});
