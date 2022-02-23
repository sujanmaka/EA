package edu.miu.cs544.sujan.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue
    private Long id;

    private String street;
    private String city;
    private String zipCode;
    private String state;

    public Address() {
    }

    public Address(String street, String city, String zipCode, String state) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
    }
}