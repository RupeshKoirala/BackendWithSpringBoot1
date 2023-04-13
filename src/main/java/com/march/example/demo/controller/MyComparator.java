package com.march.example.demo.controller;

import com.march.example.demo.entity.Employee;

import java.util.Comparator;
import java.util.List;

public class MyComparator implements Comparator<Employee> {


    @Override
    public int compare(Employee o1, Employee o2) {
        return o2.getFirst_name().compareTo(o1.getFirst_name());
    }


}
