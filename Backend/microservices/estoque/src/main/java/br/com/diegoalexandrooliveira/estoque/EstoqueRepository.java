package br.com.diegoalexandrooliveira.estoque;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EstoqueRepository {

    private final Map<Integer, Estoque> repositorio = new HashMap<>();

    public Estoque salvar(Estoque estoque) {
        return repositorio.putIfAbsent(estoque.getId(), estoque);
    }

    public List<Estoque> recuperarTodos() {
        return new ArrayList<>(repositorio.values());
    }

}
