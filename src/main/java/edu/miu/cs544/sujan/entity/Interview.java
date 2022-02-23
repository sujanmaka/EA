package edu.miu.cs544.sujan.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name="TYPE")
public abstract class Interview {
    @Id
    @GeneratedValue
    private Long id;
    private Date date;
    private String phone;
    private String email;

    public Interview() {
    }

    public Interview(Date date, String phone, String email) {
        this.date = date;
        this.phone = phone;
        this.email = email;
    }
}
