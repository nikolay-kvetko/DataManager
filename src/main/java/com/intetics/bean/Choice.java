package com.intetics.bean;

import javax.persistence.*;

/**
 * The class stores all the values of choice
 */
@Entity
@Table(name = "choice")
public class Choice {
    @Id
    @Column(name = "choice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
