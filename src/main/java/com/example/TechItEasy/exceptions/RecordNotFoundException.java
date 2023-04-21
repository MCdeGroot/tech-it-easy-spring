package com.example.TechItEasy.exceptions;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(){
        super();
    }

    public RecordNotFoundException(String message){
        super(message);

    }



}
