package br.com.diegoalexandrooliveira.produto;

import br.com.diegoalexandrooliveira.estoque.Estoque;
import br.com.diegoalexandrooliveira.estoque.EstoqueRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
class NovoProdutoListener {

    private final ProdutoClient produtoClient;
    private final EstoqueRepository estoqueRepository;

    @SqsListener("novoProduto")
    public void novoProduto(String json) throws JsonProcessingException {
        log.info("Recebendo um novo produto: " + json);
        Map<String, Integer> map = new ObjectMapper().readValue(json, Map.class);
        Integer id = map.get("id");

        Produto produto = produtoClient.recuperaPorId(id);

        estoqueRepository.salvar(new Estoque(produto));
    }

}
