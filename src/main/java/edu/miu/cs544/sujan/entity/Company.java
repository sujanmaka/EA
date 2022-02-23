package edu.miu.cs544.sujan.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
public class Company {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @OneToOne(cascade = CascadeType.PERSIST,targetEntity = Address.class)
    private Address address;

    public Company() {
    }

    public Company(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}