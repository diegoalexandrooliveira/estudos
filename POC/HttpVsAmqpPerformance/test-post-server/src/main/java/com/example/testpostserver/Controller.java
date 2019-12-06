package com.example.testpostserver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class Controller {

    @Autowired
    private Repository repository;

    @PostMapping("/api/model")
    public Model save(@RequestBody Model model) {

        model.setCreated(LocalDateTime.now());
        model = repository.save(model);

        return model;
    }

}
