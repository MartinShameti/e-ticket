package org.internship.entity;

import jakarta.persistence.*;

@Entity
public class Police {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String badgeNumber;

    public Police() {}

    public  Police(String name, String badgeNumber) {
        this.name = name;
        this.badgeNumber = badgeNumber;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBadgeNumber() { return badgeNumber; }
    public void setBadgeNumber(String badgeNumber) { this.badgeNumber = badgeNumber; }

    @Override
    public String toString() {
        return "Officer{id=" + id + ", name='" + name + "', badge='" + badgeNumber + "'}";
    }

}