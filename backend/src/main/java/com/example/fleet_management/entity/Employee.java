package com.example.fleet_management.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Employee {
    @Id
    private String email;

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;

    private String firstName;
    private String lastName;

    private String password; //remember hashing
    private String role;

    public Employee(){}
    public Employee(String firstName, String lastName, String email, String password, String role){
        this.firstName=firstName;
        this.lastName=lastName;
        this.password=password;
        this.role=role;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(){
        this.email=email;
    }

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(){
        this.firstName=firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(){
        this.lastName=lastName;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(){
        this.password=password;
    }

    public String getRole(){
        return role;
    }
    public void setRole(){
        this.role=role;
    }

    @OneToMany(mappedBy = "driver")
    private List<Matatu> matatus;

}
