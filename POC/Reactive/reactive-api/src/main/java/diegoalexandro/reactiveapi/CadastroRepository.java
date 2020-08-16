package diegoalexandro.reactiveapi;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface CadastroRepository extends ReactiveCrudRepository<Cadastro, UUID> {
}
