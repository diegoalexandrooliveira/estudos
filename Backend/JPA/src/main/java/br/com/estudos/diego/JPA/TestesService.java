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
import br.com.estudos.diego.JPA.pessoa.domain.Contas;
import br.com.estudos.diego.JPA.pessoa.domain.Motorista;
import br.com.estudos.diego.JPA.pessoa.domain.MotoristaRepository;
import br.com.estudos.diego.JPA.pessoa.domain.Sexo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


@Service
public class TestesService {

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

    @Autowired
    private MotoristaRepository motoristaRepository;


    public void testes() throws IOException {
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


        byte[] foto = new FileInputStream("./src/main/resources/assets/hibernate.png").readAllBytes();


        Carro golPreto = Carro.builder()
                .modelo(gol)
                .acessorios(Arrays.asList(arCondicionado, vidros))
                .cor("Preto")
                .foto(foto)
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

        Motorista diego = Motorista.builder()
                .numeroCNH("1010")
                .cpf("408596")
                .dataNascimento(LocalDate.now())
                .nome("Diego")
                .sexo(Sexo.MASCULINO)
                .telefones(Collections.singletonList("(18) 3311-1111"))
                .contas(Collections.singletonList(Contas.builder()
                        .banco("Itau")
                        .numeroConta("12354-5")
                        .build()))
                .build();

        diego = motoristaRepository.save(diego);

        Optional<Motorista> motoristaOptional = motoristaRepository.findById(diego.getCodigo());

        motoristaOptional.ifPresent(motorista -> {

            // Resolver LAZY
            // Solucao 1 - Criar uma query para buscar motoristas com telefones.
            Motorista allPhonesByMotorista = motoristaRepository.findAllTelefonesByCodigoMotorista(motorista.getCodigo());
            System.out.println(allPhonesByMotorista.getTelefones());

            // Solucao 2 - Colocar @Transacional no metodo que faz o find. O metodo nao pode ser chamado
            // por outro metodo que realiza transacoes no DB

        });
    }

    @Transactional
    public List<String> getTelefonesFromMotorista(Long codigo) {
        Optional<List<String>> telefones = motoristaRepository.findById(codigo)
                .map(Motorista::getTelefones);
        List<String> result = new ArrayList<>();
        telefones.ifPresent(result::addAll);
        return result;
    }

    @Transactional
    public List<Contas> getContasFromMotorista(Long codigo) {
        Optional<List<Contas>> contas = motoristaRepository.findById(codigo)
                .map(Motorista::getContas);
        List<Contas> result = new ArrayList<>();
        contas.ifPresent(result::addAll);
        return result;
    }

    public Optional<byte[]> getFotoCarro() {
        return carroRepository.findById(1L).map(Carro::getFoto);
    }
}
