package br.com.estudos.diego.JPA;

import br.com.estudos.diego.JPA.pessoa.domain.Contas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class Runner implements CommandLineRunner {


    @Autowired
    private TestesService testesService;

    @Override
    public void run(String... args) throws IOException {

        testesService.testes();

        List<Contas> contasFromMotorista = testesService.getContasFromMotorista(1L);
        contasFromMotorista.forEach(System.out::println);

        Optional<byte[]> fotoCarro = testesService.getFotoCarro();

        File file = new File("./src/main/resources/assets/novo_hibernate.png");
        if (file.exists()) {
            file.delete();
        }

        fotoCarro.ifPresent(bytes -> {

            try {
                FileOutputStream fileOutputStream = new FileOutputStream("./src/main/resources/assets/novo_hibernate.png");
                fileOutputStream.write(bytes);
                fileOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }


}
