package com.example.TechItEasy.dto.input;

// Deze klasse wordt gebruikt in je Get, Post en Put methodes. Overal waat je een Television als returnwaarde wilt geven.
// Een input DTO behoet geen validatie

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelevisionInputDto {
    public Long id;
    public String type;
    public String brand;
    public String name;
    public Double price;
    public Double availableSize;
    public Double refreshRate;
    public String screenType;
    public String screenQuality;
    public Boolean smartTv;
    public Boolean wifi;
    public Boolean voiceControl;
    public Boolean hdr;
    public Boolean bluetooth;
    public Boolean ambiLight;
    public Integer originalStock;
    public Integer sold;
    public Long rcId;

}
