package com.example.testamqpserver;

import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "post_amqp")
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
