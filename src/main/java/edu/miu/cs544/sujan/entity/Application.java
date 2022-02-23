package edu.miu.cs544.sujan.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Application {
    @Id
    @GeneratedValue
    private Long id;

    private Date date;
    private String resumeVersion;

    @OneToOne(cascade = CascadeType.PERSIST, targetEntity = Job.class)
    private Job job;

    public Application() {
    }

    public Application(Date date, String resumeVersion, Job job) {
        this.date = date;
        this.resumeVersion = resumeVersion;
        this.job = job;
    }
}