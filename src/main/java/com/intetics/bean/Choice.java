package com.intetics.bean;

import javax.persistence.*;

/**
 * todo[a.chervyakovsky] place meaningful javadoc here
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
