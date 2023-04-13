package com.march.example.demo.controller;

public class NameNotValid extends RuntimeException{
    public NameNotValid(String message){
        super(message);
    }
}