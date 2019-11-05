package br.com.estudos.diego.JPA;

import br.com.estudos.diego.JPA.aluguel.domain.Aluguel;
import br.com.estudos.diego.JPA.aluguel.domain.AluguelRepository;
import br.com.estudos.diego.JPA.aluguel.domain.ApoliceSeguro;
import br.com.estudos.diego.JPA.carro.acessorio.domain.Acessorio;
import br.com.estudos.diego.JPA.carro.acessorio.domain.AcessorioRepository;
import br.com.estudos.diego.JPA.carro.domain.Carro;
import br.com.estudos.diego.JPA.carro.domain.CarroRepository;
import br.com.estudos.diego.JPA.carro.fabricante.domain.Fabricante;
import br.com.estudos.diego.JPA.carro.fabricante.domain.FabricanteRepository;
import br.com.estudos.diego.JPA.carro.modelo.domain.Categoria;
import br.com.estudos.diego.JPA.carro.modelo.domain.Modelo;
import br.com.estudos.diego.JPA.carro.modelo.domain.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private FabricanteRepository fabricanteRepository;
    @Autowired
    private ModeloRepository modeloRepository;
    @Autowired
    private AcessorioRepository acessorioRepository;
    @Autowired
    private CarroRepository carroRepository;
    @Autowired
    private AluguelRepository aluguelRepository;

    @Override
    public void run(String... args) throws Exception {

        Fabricante vw = Fabricante.builder().nome("VW").build();

        fabricanteRepository.save(vw);

        Modelo gol = Modelo.builder()
                .descricao("Gol")
                .categoria(Categoria.HATCH_COMPACTO)
                .fabricante(vw).build();

        modeloRepository.save(gol);

        Acessorio arCondicionado = Acessorio.builder()
                .descricao("Ar-condicionado")
                .build();

        acessorioRepository.save(arCondicionado);

        Acessorio vidros = Acessorio.builder()
                .descricao("Vidros eletricos")
                .build();

        acessorioRepository.save(vidros);


        Carro golPreto = Carro.builder()
                .modelo(gol)
                .acessorios(Arrays.asList(arCondicionado, vidros))
                .cor("Preto")
                .build();

        carroRepository.save(golPreto);

        Aluguel aluguelGol = Aluguel.builder()
                .carro(golPreto)
                .valorTotal(new BigDecimal(500))
                .apoliceSeguro(ApoliceSeguro.builder()
                        .protecaoCausasNaturais(true)
                        .protecaoRoubo(true)
                        .valorFranquia(new BigDecimal(200))
                        .build())
                .dataPedido(Calendar.getInstance())
                .dataEntrega(new Date())
                .dataDevolucao(new Date())
                .build();

        aluguelRepository.save(aluguelGol);


    }
}
