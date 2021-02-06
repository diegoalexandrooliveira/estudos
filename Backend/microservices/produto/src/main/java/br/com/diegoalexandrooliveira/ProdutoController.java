package br.com.diegoalexandrooliveira;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
class ProdutoController {


    private final ProdutoRepository produtoRepository;
    private final NovoProdutoSQS novoProdutoSQS;
    private final ProdutoExcluidoSQS produtoExcluidoSQS;


    @GetMapping
    public ResponseEntity<List<Produto>> recuperarTodos() {
        return ResponseEntity.ok(produtoRepository.recuperarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> recuperarPorId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(produtoRepository.recuperarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody NovoProduto novoProduto) {
        Produto produto = produtoRepository.salvar(novoProduto.toModel());
        novoProdutoSQS.enviar(produto);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Integer id) {
        produtoRepository.excluir(id);
        produtoExcluidoSQS.enviar(id);
        return ResponseEntity.noContent().build();
    }


}
