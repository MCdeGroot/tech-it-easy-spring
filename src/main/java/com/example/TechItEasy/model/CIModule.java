package com.example.TechItEasy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ci-modules")
public class CIModule {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private Double price;

}
