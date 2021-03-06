package edu.miu.cs544.sujan.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("Client")
public class Client extends Company{
    private String companyMission;
    private String reason;
    private String website;

    public Client() {
        super();
    }

    public Client(String name, Address address, String companyMission, String reason, String website) {
        super(name, address);
        this.companyMission = companyMission;
        this.reason = reason;
        this.website = website;
    }
}