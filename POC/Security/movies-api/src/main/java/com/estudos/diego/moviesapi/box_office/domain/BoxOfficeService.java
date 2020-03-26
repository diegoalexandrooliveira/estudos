package com.estudos.diego.moviesapi.box_office.domain;

import com.estudos.diego.moviesapi.box_office.infrastructure.BoxOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoxOfficeService {


    @Autowired
    private BoxOfficeRepository repository;


    public Optional<BoxOffice> getByName(String movieName) {
        return Optional.ofNullable(repository.get(movieName));
    }
}
