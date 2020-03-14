package br.com.diego.estudos.serverWithVault;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/secret")
public class Controller {

    @Autowired
    private Environment environment;


    @GetMapping(path = "/1")
    public ResponseEntity getSecret() {
        return ResponseEntity.ok(environment.getProperty("valor-secreto-1"));
    }

    @GetMapping(path = "/2")
    public ResponseEntity getSecret2() {
        return ResponseEntity.ok(environment.getProperty("valor-secreto-2"));
    }
}
