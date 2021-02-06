package br.com.diegoalexandrooliveira.estoque;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/estoque")
@RequiredArgsConstructor
public class EstoqueController {

    private final EstoqueRepository estoqueRepository;

    @GetMapping
    public ResponseEntity<List<Estoque>> recuperaTodosProdutos() {
        return ResponseEntity.ok(estoqueRepository.recuperarTodos());
    }
}
