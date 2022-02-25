package edu.miu.cs544.sujan.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@DiscriminatorValue("Hir")
public class HiringManagerInterview extends Interview {
    @Id
    @GeneratedValue
    private Long id;

    @Version
    private int version;

    private int teamSize;
    private LocalDate startDate;

    public HiringManagerInterview() {
        super();
    }

    public HiringManagerInterview(LocalDate date, String phone, String email, int teamSize, LocalDate startDate) {
        super(date, phone, email);
        this.teamSize = teamSize;
        this.startDate = startDate;
    }
}