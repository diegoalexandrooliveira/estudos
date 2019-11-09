package br.com.estudos.diego.JPA.pessoa.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoristaRepository extends CrudRepository<Motorista, Long> {

    @Query("select m from Motorista m join fetch m.telefones where m.codigo = ?1")
    Motorista findAllTelefonesByCodigoMotorista(Long codigo);
}
