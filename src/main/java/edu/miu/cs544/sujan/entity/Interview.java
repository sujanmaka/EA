package edu.miu.cs544.sujan.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name="TYPE")
public abstract class Interview {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    private String phone;
    private String email;

    public Interview() {
    }

    public Interview(LocalDate date, String phone, String email) {
        this.date = date;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "id=" + id +
                ", date=" + date +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
