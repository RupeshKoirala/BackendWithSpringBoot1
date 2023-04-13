package com.march.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee implements Comparable<Employee>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String first_name;
    private String last_name;

    private int age;

    private Date date_of_birth;

    private Date start_date;

    private Date end_date;

    private boolean termination;

    private String last_salary;

    private int department_id;

    @OneToOne
    @JoinColumn
    private Address address;

    @Override
    public int compareTo(Employee o) {
        if(o.age > this.age){
            return 1;
        }else if(o.age==this.age){
            return 0;
        } else{
            return -1;
        }
    }
}
