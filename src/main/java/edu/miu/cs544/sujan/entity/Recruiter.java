package edu.miu.cs544.sujan.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Recruiter")
public class Recruiter extends Company {
    @OneToMany(cascade = CascadeType.PERSIST, targetEntity = Client.class)
    private List<Client> clients;
    public Recruiter() {
    }

    public Recruiter(String name, Address address) {
        super(name, address);
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Recruiter{" +
                "clients=" + clients +
                '}';
    }
}