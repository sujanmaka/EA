package edu.miu.cs544.sujan.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Job {
    @Id
    @GeneratedValue
    private Long id;

    @Version
    private int version;

    private String title;
    private double salary;
    @OneToMany(cascade = CascadeType.PERSIST,targetEntity = Skill.class)
    private List<Skill> skills;
    @ManyToOne(cascade = CascadeType.PERSIST,targetEntity = Company.class)
    private Company company;

    @OneToMany(cascade = CascadeType.PERSIST, targetEntity = Interview.class)
    private List<Interview> interviews;

    public Job() {
    }

    public Job(String title, double salary) {
        this.title = title;
        this.salary = salary;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", salary=" + salary +
                ", skills=" + skills +
                ", company=" + company +
                ", interviews=" + interviews +
                '}';
    }
}