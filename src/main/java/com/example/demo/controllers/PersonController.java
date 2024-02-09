package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Person;
import com.example.demo.entities.PersonRepository;

import lombok.NonNull;

@Controller
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person createPerson(@NonNull Person person) {
        return personRepository.save(person);
    }

    @Transactional // wichtig, siehe Kommentar zu identical
    public void test() {
        // given a Person with an ID
        Long id = createPerson(Person.withName("Jone Doe")).id();

        // when the Person is loaded
        Person p1 = personRepository.getById(id);
        notNull(p1);

        // when the Person is loaded again
        Person p2 = personRepository.getById(id);
        notNull(p2);

        // p1 ist nur identisch zu p2 (p1 == p2), wenn diese Methode innerhalb derselben Transaction ausgeführt wird
        identical(p1, p2);
    }

    private static void notNull(Object o) {
        if(o == null) throw new NullPointerException();
    }

    private static void identical(Object o1, Object o2) {
        if(o1 != o2) throw new IllegalStateException("not identical");
    }
}