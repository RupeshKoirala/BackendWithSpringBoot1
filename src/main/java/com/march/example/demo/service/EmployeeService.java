package com.march.example.demo.service;

import com.march.example.demo.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    public List<Employee> getAll();

    public String name();
}
