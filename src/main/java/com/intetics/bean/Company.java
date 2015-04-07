package com.intetics.bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Кузнец on 05.04.2015.
 */

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @Column(nullable = false)
    private String name;

    @Column
    private String address;

    @Column
    private String language;

    @Column
    private String theme;

    @Column
    private Byte[] logo;

    @OneToMany(mappedBy = "company", fetch=FetchType.LAZY, orphanRemoval=true, cascade = {CascadeType.ALL})
    private Set<Users> users = new HashSet<Users>();

    @OneToMany(mappedBy = "company", fetch=FetchType.LAZY, orphanRemoval=true, cascade = {CascadeType.ALL})
    private Set<EntityName> entityNames = new HashSet<EntityName>();

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Byte[] getLogo() {
        return logo;
    }

    public void setLogo(Byte[] logo) {
        this.logo = logo;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }

    public Set<EntityName> getEntityNames() {
        return entityNames;
    }

    public void setEntityNames(Set<EntityName> entityNames) {
        this.entityNames = entityNames;
    }
}
