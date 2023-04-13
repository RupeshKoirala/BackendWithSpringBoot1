package com.march.example.demo.service;

import com.march.example.demo.entity.Employee;
import com.march.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeeRepository em;
    @Override
    public List<Employee> getAll() {
        //1.Using size and get to iterate the list
        var allFields = em.findAll();
        for(int i=0; i<allFields.size(); i++){
            String name = allFields.get(i).getFirst_name();
            String reversedString = "";
            for(int j=name.length()-1; j>=0; j--){
                reversedString+=name.charAt(j);
            }
            System.out.println(reversedString);
            allFields.get(i).setFirst_name(reversedString);
        }
        return allFields;
    }

    @Override
    public String name() {
        return null;
    }
}
