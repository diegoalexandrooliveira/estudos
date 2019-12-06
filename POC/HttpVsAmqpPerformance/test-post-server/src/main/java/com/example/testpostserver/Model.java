package com.example.testpostserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "post")
@Data
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String description;

    @Setter
    private LocalDateTime created = LocalDateTime.now();
}
