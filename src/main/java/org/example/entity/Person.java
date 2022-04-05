package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="persons")
@Getter
@Setter
@ToString
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="persons_name")
    private String name;
    private int age;

    public Person( String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

}
