package br.com.diegoalexandrooliveira.produto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080/api/produtos/", name = "produtos")
public interface ProdutoClient {

    @GetMapping("/{id}")
    Produto recuperaPorId(@PathVariable("id") Integer id);

}
