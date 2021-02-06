package br.com.diegoalexandrooliveira;


import lombok.AllArgsConstructor;

@AllArgsConstructor
class NovoProduto {

    private final String nome;
    private final String nomeCategoria;

    Produto toModel() {
        Categoria categoria = new Categoria(nomeCategoria);
        return new Produto(nome, categoria);
    }
}
