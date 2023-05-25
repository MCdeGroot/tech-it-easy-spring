package com.example.TechItEasy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "remote-controllers")
public class RemoteController {
    @Id
    @GeneratedValue
    private Long id;
    private String compatibleWith;
    private String batteryType;
    private String name;
    private String brand;
    private Double price;
    private Integer originalStock;

    //dit is de target side en televisoon is de owner side van de relatie
    @OneToOne(mappedBy = "remotecontroller")
    private Television television;

}
