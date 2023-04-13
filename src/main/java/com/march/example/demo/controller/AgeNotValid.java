package com.march.example.demo.controller;

public class AgeNotValid extends RuntimeException{
    public AgeNotValid(String message){
        super(message);
    }
}
