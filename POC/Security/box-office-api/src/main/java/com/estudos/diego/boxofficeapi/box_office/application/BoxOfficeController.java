package com.estudos.diego.boxofficeapi.box_office.application;

import com.estudos.diego.boxofficeapi.box_office.domain.BoxOffice;
import com.estudos.diego.boxofficeapi.box_office.domain.BoxOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/boxOffice")
@CrossOrigin
public class BoxOfficeController {

    @Autowired
    private BoxOfficeService boxOfficeService;


    @GetMapping(path = "/{movieName}")
    public HttpEntity<BoxOffice> findByMovie(@PathVariable("movieName") String movieName) {
        return ResponseEntity.ok(boxOfficeService.findByMovieName(movieName).orElse(null));
    }

}
