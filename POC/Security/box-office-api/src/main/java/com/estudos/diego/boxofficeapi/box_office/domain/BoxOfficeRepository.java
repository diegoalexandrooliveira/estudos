package com.estudos.diego.boxofficeapi.box_office.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BoxOfficeRepository extends JpaRepository<BoxOffice, UUID> {


    Optional<BoxOffice> findByMovieName(String movieName);
}
