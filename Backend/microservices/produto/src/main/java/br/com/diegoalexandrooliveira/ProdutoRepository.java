package br.com.diegoalexandrooliveira;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
class ProdutoRepository {


    private final Map<Integer, Produto> repositorio = new HashMap<>();


    Produto salvar(Produto produto) {
        repositorio.putIfAbsent(produto.getId(), produto);
        return produto;
    }

    void excluir(Integer id) {
        repositorio.remove(id);
    }

    List<Produto> recuperarTodos() {
        return new ArrayList<>(repositorio.values());
    }


    Produto recuperarPorId(Integer id) {
        return repositorio.get(id);
    }
}
