package com.example.TechItEasy.controllers;

import com.example.TechItEasy.exceptions.RecordNotFoundException;
import com.example.TechItEasy.model.Television;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    public List<Television> televisionDataBase = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Television>> getAllTelevisions(){
        return new ResponseEntity<>(televisionDataBase, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevisionByID(@PathVariable int id) {
        if (id < 0 || id >= televisionDataBase.size()) {
            throw new IndexOutOfBoundsException(id + " cannot be found");
        } return new ResponseEntity<>(televisionDataBase.get(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Television> addTelevision(@RequestBody Television t){
        televisionDataBase.add(t);
        return new ResponseEntity<>(t, HttpStatus.CREATED); //hier returnen we dat we Television t hebben toegevoegd
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTelevisionByID(@PathVariable int id) {
        if (id < 0 || id >= televisionDataBase.size()) {
            throw new IndexOutOfBoundsException(id + " cannot be found");
        } televisionDataBase.remove(televisionDataBase.get(id));
        return new ResponseEntity<>("Deze tv is verwijderd!!", HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTelevisionByID(@PathVariable int id, @RequestBody Television t) {
        if (id < 0 || id >= televisionDataBase.size()) {
            throw new RecordNotFoundException(id + " cannot be found");
        }
          televisionDataBase.set(id, t);
            return new ResponseEntity<>(t, HttpStatus.OK);
    }

}
