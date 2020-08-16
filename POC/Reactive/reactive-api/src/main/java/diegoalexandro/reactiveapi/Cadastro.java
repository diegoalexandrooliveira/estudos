package diegoalexandro.reactiveapi;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import java.util.Objects;
import java.util.UUID;

@Getter
public class Cadastro implements Persistable<UUID> {

  @Id
  private UUID id;
  private String nome;

  @Transient
  private boolean isNew;

  public Cadastro(UUID id, String nome) {
    this.isNew = Objects.isNull(id);
    this.id = isNew ? UUID.randomUUID() : id;
    this.nome = nome;
  }

  @Override
  @JsonIgnore
  public boolean isNew() {
    return this.isNew;
  }
}
