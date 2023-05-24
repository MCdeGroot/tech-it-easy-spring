package com.example.TechItEasy.controllers;

import com.example.TechItEasy.model.Television;
import com.example.TechItEasy.repository.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    private final TelevisionRepository televisionRepository;
    public TelevisionController(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<Television>> getAllTelevisions(){
        return ResponseEntity.ok(televisionRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevisionByID(@PathVariable Long id) {
        Optional<Television> television =  televisionRepository.findById(id);

        if (television.isEmpty()) {
            throw new IndexOutOfBoundsException("Television with id-number" + id + " cannot be found");
        } else {
            return ResponseEntity.ok().body(television.get());
        }
    }
    @PostMapping
    public ResponseEntity<Television> addTelevision(@RequestBody Television television){
        Television televisionPostMap = televisionRepository.save(television);
        return ResponseEntity.ok().body(televisionPostMap);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTelevisionByID(@PathVariable Long id) {
        if (televisionRepository.findById(id).isEmpty()) {
            throw new IndexOutOfBoundsException("Television with id-number" + id + " cannot be found");
        } else {
            televisionRepository.deleteById(id);
            return ResponseEntity.noContent().build();// Hier snap ik niet zo goed waarom dit moet
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTelevisionByID(@PathVariable Long id, @RequestBody Television t) {
        if (televisionRepository.findById(id).isEmpty()) {
            throw new IndexOutOfBoundsException("Television with id-number" + id + " cannot be found");
        } else {
            Television televisionPutMap = televisionRepository.findById(id).get();
            televisionPutMap.setType(t.getType());
            televisionPutMap.setBrand(t.getBrand());
            televisionPutMap.setName(t.getName());
            televisionPutMap.setPrice(t.getPrice());
            televisionPutMap.setAvailableSize(t.getAvailableSize());
            televisionPutMap.setRefreshRate(t.getRefreshRate());
            televisionPutMap.setScreenType(t.getScreenType());
            televisionPutMap.setScreenQuality(t.getScreenQuality());
            televisionPutMap.setSmartTv(t.getSmartTv());
            televisionPutMap.setWifi(t.getWifi());
            televisionPutMap.setVoiceControl(t.getVoiceControl());
            televisionPutMap.setHdr(t.getHdr());
            televisionPutMap.setBluetooth(t.getBluetooth());
            televisionPutMap.setAmbiLight(t.getAmbiLight());
            televisionPutMap.setOriginalStock(t.getOriginalStock());
            televisionPutMap.setSold(t.getSold());

            Television television = televisionRepository.save(televisionPutMap);

            return ResponseEntity.ok().body(television);
        }
    }
}
