package com.example.fleet_management.entity;

import jakarta.persistence.*;

@Entity
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String location;

    @ManyToOne
    @JoinColumn (name="marshall_id")
    private Employee marshall;

    //default constructor
    public Stage(){}
    public Stage(String name, String location){
        this.name=name;
        this.location=location;
    }//constructor with all fields

    //getters and setters
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }

    public String getName(){
        return name;
    }
    public void setName(){
        this.name=name;
    }

    public String getLocation(){
        return location;
    }
    public void setLocation(){
        this.location=location;
    }

    public Employee getMarshall(){
        return marshall;
    }
    public void setMarshall(Employee marshall){
        this.marshall=marshall;
    }

}
