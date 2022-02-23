package edu.miu.cs544.sujan.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("Hir")
public class HiringManagerInterview extends Interview {
    @Id
    @GeneratedValue
    private Long id;

    private int teamSize;
    private Date startDate;

    public HiringManagerInterview() {
        super();
    }

    public HiringManagerInterview(Date date, String phone, String email, int teamSize, Date startDate) {
        super(date, phone, email);
        this.teamSize = teamSize;
        this.startDate = startDate;
    }
}