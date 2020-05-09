package com.example.swarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/status")
public class Controller {


    @Autowired
    private CustomHealthIndicator customHealthIndicator;


    @GetMapping(path = "/disable")
    public ResponseEntity<String> disable() {
        customHealthIndicator.disable();
        return ResponseEntity.ok("disabled");
    }
}
