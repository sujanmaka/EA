package edu.miu.cs544.sujan.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    private String quest;

    public Question() {
    }

    public Question(String quest) {
        this.quest = quest;
    }
}