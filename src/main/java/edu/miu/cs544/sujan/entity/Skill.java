package edu.miu.cs544.sujan.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String experience;
    private String description;
    private String language;

    public Skill() {
    }

    public Skill(String name, String experience, String description, String language) {
        this.name = name;
        this.experience = experience;
        this.description = description;
        this.language = language;
    }
}