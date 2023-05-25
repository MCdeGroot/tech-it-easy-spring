package com.example.TechItEasy.service;

import com.example.TechItEasy.dto.input.TelevisionInputDto;
import com.example.TechItEasy.dto.output.TelevisionOutputDto;
import com.example.TechItEasy.exceptions.RecordNotFoundException;
import com.example.TechItEasy.model.RemoteController;
import com.example.TechItEasy.model.Television;
import com.example.TechItEasy.repository.RemoteControllerRepository;
import com.example.TechItEasy.repository.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelevisionService {
    //maken een repository aan die met de database kan communiceren
    private final TelevisionRepository televisionRepository;
    private final RemoteControllerRepository remoteControllerRepository;


    public TelevisionService(TelevisionRepository televisionRepository, RemoteControllerRepository remoteControllerRepository) {
        this.televisionRepository = televisionRepository;
        this.remoteControllerRepository = remoteControllerRepository;
    }



    public TelevisionOutputDto getTelevisionById(Long id) {
        Television television = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television with id-number" + id + " cannot be found"));
        TelevisionOutputDto televisionOutputDto = transferTelevisionModelToOutputDto(television);
        return televisionOutputDto;
    }

    public List<TelevisionOutputDto> getAllTelevisions() {
        ArrayList<TelevisionOutputDto> allTelevisionOutputDtos = new ArrayList<>();
        List<Television> televisions = televisionRepository.findAll();
        if (televisions.isEmpty()) {
            throw new IndexOutOfBoundsException("There are currently no objects in the database");
        }
        for (Television t : televisions
        ) {
            TelevisionOutputDto televisionOutputDto = transferTelevisionModelToOutputDto(t);
            allTelevisionOutputDtos.add(televisionOutputDto);
        }
        return allTelevisionOutputDtos;
    }

    //tv toevoegen. je maakt een TvOutputDTO object met onderstaande methode. geven de input mee als parameters en vervolgens slaan we die op in een object TV.
    // deze gaan we opslaan in de database via de geinstantieerde repository (injection)
    // vervolgens zetten we het object om in een outputDTO die we kunnen returnen aan de client.
    public TelevisionOutputDto addTelevision(TelevisionInputDto televisionInputDto){
        Television television = transferInputDtoToTelevisionModel(televisionInputDto);
        televisionRepository.save(television);
        TelevisionOutputDto televisionOutputDto = transferTelevisionModelToOutputDto(television);
        return televisionOutputDto;
    }

    public TelevisionOutputDto updateTelevision(Long id, TelevisionInputDto televisionInputDto) {

        //Waarom moet ik hier per se een optional?????
        Television television = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television with id-number" + id + " cannot be found"));
        television.setAmbiLight(televisionInputDto.getAmbiLight());
        television.setAvailableSize(televisionInputDto.getAvailableSize());
        television.setAmbiLight(televisionInputDto.getAmbiLight());
        television.setBluetooth(televisionInputDto.getBluetooth());
        television.setBrand(televisionInputDto.getBrand());
        television.setHdr(televisionInputDto.getHdr());
        television.setName(televisionInputDto.getName());
        television.setOriginalStock(televisionInputDto.getOriginalStock());
        television.setPrice(televisionInputDto.getPrice());
        television.setRefreshRate(televisionInputDto.getRefreshRate());
        television.setScreenQuality(televisionInputDto.getScreenQuality());
        television.setScreenType(televisionInputDto.getScreenType());
        television.setSmartTv(televisionInputDto.getSmartTv());
        television.setSold(televisionInputDto.getSold());
        television.setType(televisionInputDto.getType());
        television.setVoiceControl(televisionInputDto.getVoiceControl());
        television.setWifi(televisionInputDto.getWifi());

        televisionRepository.save(television);

        return transferTelevisionModelToOutputDto(television);
    }

    public String deleteTelevision (Long id) {
        Television television = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television with id " + id + " doesn't exist"));

        televisionRepository.delete(television);

        return "Well well I hope you know what you're doing, because you just removed " + television.getName() + "!";
    }

    public TelevisionOutputDto assignRcToTv(Long id, Long rcId){
        Television television = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television with id " + id + " doesn't exist"));
        RemoteController remoteController = remoteControllerRepository.findById(rcId).orElseThrow(() -> new RecordNotFoundException("RemoteController with id " + rcId + " doesn't exist"));
        television.setRemoteController(remoteController);
        television.setRemoteController(remoteController);
        televisionRepository.save(television);
        return transferTelevisionModelToOutputDto(television);

    }


    // we maken hier een methode aan die er voor zorgt dat de outputDTO wordt gevuld met waarden uit de model
    public TelevisionOutputDto transferTelevisionModelToOutputDto(Television television) {
        TelevisionOutputDto televisionOutputDto = new TelevisionOutputDto();
        televisionOutputDto.id = television.getId();
        televisionOutputDto.type = television.getType();
        televisionOutputDto.brand = television.getBrand();
        televisionOutputDto.name = television.getName();
        televisionOutputDto.price = television.getPrice();
        televisionOutputDto.availableSize = television.getAvailableSize();
        televisionOutputDto.refreshRate = television.getRefreshRate();
        televisionOutputDto.screenType = television.getScreenType();
        televisionOutputDto.screenQuality = television.getScreenQuality();
        televisionOutputDto.smartTv = television.getSmartTv();
        televisionOutputDto.wifi = television.getWifi();
        televisionOutputDto.voiceControl = television.getVoiceControl();
        televisionOutputDto.hdr = television.getHdr();
        televisionOutputDto.bluetooth = television.getBluetooth();
        televisionOutputDto.ambiLight = television.getAmbiLight();
        televisionOutputDto.originalStock = television.getOriginalStock();
        televisionOutputDto.sold = television.getSold();

        return televisionOutputDto;
    }

    public Television transferInputDtoToTelevisionModel(TelevisionInputDto televisionInputDto) {
        Television television = new Television();
        television.setType(televisionInputDto.type);
        television.setBrand(televisionInputDto.brand);
        television.setName(televisionInputDto.name);
        television.setPrice(televisionInputDto.price);
        television.setAvailableSize(televisionInputDto.availableSize);
        television.setRefreshRate(televisionInputDto.refreshRate);
        television.setScreenType(televisionInputDto.screenType);
        television.setScreenQuality(televisionInputDto.screenQuality);
        television.setSmartTv(televisionInputDto.smartTv);
        television.setVoiceControl(televisionInputDto.voiceControl);
        television.setHdr(televisionInputDto.hdr);
        television.setBluetooth(televisionInputDto.bluetooth);
        television.setAmbiLight(televisionInputDto.ambiLight);
        television.setOriginalStock(televisionInputDto.originalStock);
        television.setSold(televisionInputDto.sold);

        return television;
    }


}
