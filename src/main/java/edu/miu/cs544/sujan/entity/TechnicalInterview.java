package edu.miu.cs544.sujan.entity;

import edu.miu.cs544.sujan.Location;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("Tech")
public class TechnicalInterview extends Interview {
    @Id
    @GeneratedValue
    private Long id;

    @Version
    private int version;

    private int duration;
    @Enumerated(EnumType.STRING)
    private Location location;
    @OneToMany(cascade = CascadeType.PERSIST, targetEntity = Question.class)
    private List<Question> questions;

    public TechnicalInterview() {
        super();
    }

    public TechnicalInterview(LocalDate date, String phone, String email, int duration, Location location, List<Question> questions) {
        super(date, phone, email);
        this.duration = duration;
        this.location = location;
        this.questions = questions;
    }
}