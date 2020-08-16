package diegoalexandro.reactiveapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import java.time.Duration;
import java.util.UUID;

@RestController
@RequestMapping("/api/cadastros")
@CrossOrigin
public class CadastroController {

  private FluxSink<Cadastro> listener;

  @Autowired
  private CadastroRepository cadastroRepository;

  @GetMapping
  public Flux<Cadastro> recuperar() {
    return cadastroRepository.findAll().delayElements(Duration.ofSeconds(2));
  }

  @GetMapping(path = "/persistente")
  public Flux<Cadastro> recuperarPersistente() {
    Flux<Cadastro> novosCadastros = Flux.create(stringFluxSink -> {
      listener = stringFluxSink;
    });
    Flux<Cadastro> todosCadastros = cadastroRepository.findAll();
    return Flux.concat(todosCadastros, novosCadastros);
  }

  @PostMapping
  public Mono<Cadastro> salvar(@RequestBody Cadastro cadastro) {
    return cadastroRepository.save(cadastro).doOnSuccess(cadastroSalvo -> listener.next(cadastroSalvo));
  }
}
