package com.estudos.diego.boxofficeapi.box_office.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoxOfficeService {

    @Autowired
    private BoxOfficeRepository boxOfficeRepository;


    public Optional<BoxOffice> findByMovieName(String movieName) {
        return boxOfficeRepository.findByMovieName(movieName);
    }


}
