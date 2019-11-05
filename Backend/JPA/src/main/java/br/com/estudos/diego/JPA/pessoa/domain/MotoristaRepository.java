package br.com.estudos.diego.JPA.pessoa.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoristaRepository extends CrudRepository<Motorista, Long> {
}
