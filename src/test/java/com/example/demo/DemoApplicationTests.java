package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.controllers.PersonController;
import com.example.demo.entities.Person;
import com.example.demo.entities.PersonRepository;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private PersonController personController;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testCreatePerson() {
        Person person = Person.withName("Jone Doe");

        Person savedPerson = personController.createPerson(person);

        assertThat(savedPerson).isNotNull();
        assertThat(savedPerson.id()).isNotNull();
        assertThat(savedPerson.name()).isEqualTo(person.name());
    }

    @Test
    public void testLoadPerson() {
        personController.test();
    }
}
