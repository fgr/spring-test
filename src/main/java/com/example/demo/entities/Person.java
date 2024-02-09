package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.transaction.annotation.Transactional;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public static Person withName(String name) { return new Person(name); }

    @Transactional(readOnly = true)
    public String name() {return name; }

    public Long id() { return id; }

    @Transactional
    public void changeName(String newName) { this.name = newName; }

    public Person() {}
    public Person(String name) {this.name = name;}
}
