package com.example.TechItEasy.service;

import com.example.TechItEasy.dto.input.RemoteControllerInputDto;
import com.example.TechItEasy.dto.output.RemoteControllerOutputDto;
import com.example.TechItEasy.exceptions.RecordNotFoundException;
import com.example.TechItEasy.model.RemoteController;
import com.example.TechItEasy.repository.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RCService {

    private final RemoteControllerRepository remoteControllerRepository;

    public RCService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }

    public RemoteControllerOutputDto getRCById(Long id) {
        RemoteController remoteController = remoteControllerRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Remote controller with id-number" + id + " cannot be found"));
        RemoteControllerOutputDto remoteControllerOutputDto = transferRCModelToRCOutputDto(remoteController);
        return remoteControllerOutputDto;
    }

    public List<RemoteControllerOutputDto> getAllRC() {
        ArrayList<RemoteControllerOutputDto> allRemoteControllerOutputDtos = new ArrayList<>();
        List<RemoteController> remoteControllers = remoteControllerRepository.findAll();
        if (remoteControllers.isEmpty()) {
            throw new IndexOutOfBoundsException("There are currently no objects in the database");
        }
        for (RemoteController rc : remoteControllers) {
            RemoteControllerOutputDto remoteControllerOutputDto = transferRCModelToRCOutputDto(rc);
            allRemoteControllerOutputDtos.add(remoteControllerOutputDto);
        }
        return allRemoteControllerOutputDtos;
    }

    public RemoteControllerOutputDto addRC(RemoteControllerInputDto remoteControllerInputDto){
        RemoteController rc = transferInputDtoToRCModel(remoteControllerInputDto);
        remoteControllerRepository.save(rc);
        RemoteControllerOutputDto remoteControllerOutputDto = transferRCModelToRCOutputDto(rc);
        return remoteControllerOutputDto;
    }

    public RemoteControllerOutputDto transferRCModelToRCOutputDto(RemoteController remoteController) {
        RemoteControllerOutputDto remoteControllerOutputDto = new RemoteControllerOutputDto();
        remoteControllerOutputDto.id = remoteController.getId();
        remoteControllerOutputDto.compatibleWith = remoteController.getCompatibleWith();
        remoteControllerOutputDto.batteryType = remoteController.getBatteryType();
        remoteControllerOutputDto.name = remoteController.getName();
        remoteControllerOutputDto.brand = remoteController.getBrand();
        remoteControllerOutputDto.price = remoteController.getPrice();
        remoteControllerOutputDto.originalStock = remoteController.getOriginalStock();

        return remoteControllerOutputDto;
    }

    public RemoteController transferInputDtoToRCModel(RemoteControllerInputDto remoteControllerInputDto) {
        RemoteController remoteController = new RemoteController();

        remoteController.setId(remoteControllerInputDto.id);
        remoteController.setCompatibleWith(remoteControllerInputDto.compatibleWith);
        remoteController.setBatteryType(remoteControllerInputDto.batteryType);
        remoteController.setName(remoteControllerInputDto.name);
        remoteController.setBrand(remoteControllerInputDto.brand);
        remoteController.setPrice(remoteControllerInputDto.price);
        remoteController.setOriginalStock(remoteControllerInputDto.originalStock);

        return remoteController;
    }

}
