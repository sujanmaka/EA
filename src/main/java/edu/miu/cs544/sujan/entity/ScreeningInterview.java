package edu.miu.cs544.sujan.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@DiscriminatorValue("Scr")
public class ScreeningInterview extends Interview {
    @Id
    @GeneratedValue
    private Long id;

    @Version
    private int version;

    private String name;
    private String result;

    public ScreeningInterview() {
        super();
    }

    public ScreeningInterview(LocalDate date, String phone, String email, String name, String result) {
        super(date, phone, email);
        this.name = name;
        this.result = result;
    }
}