package com.estudos.diego.moviesapi.box_office.infrastructure;

import com.estudos.diego.moviesapi.box_office.domain.BoxOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

@Service
public class BoxOfficeRepository {

    @Autowired
    private OAuth2RestTemplate restTemplate;
    @Value("${box-office.api}")
    private String api;

    public BoxOffice get(String movieName) {
        ResponseEntity<BoxOffice> boxOfficeResponseEntity = restTemplate.exchange(api + "/" + movieName, HttpMethod.GET, null, BoxOffice.class);
        return boxOfficeResponseEntity.getBody();
    }
}
