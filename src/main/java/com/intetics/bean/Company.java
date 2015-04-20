package com.intetics.bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "language")
    private String language;

    @Column(name = "theme")
    private String theme;

    @Column(name = "logo")
    private byte[] logo;

    @OneToMany(mappedBy = "company", fetch=FetchType.LAZY, orphanRemoval=true, cascade = {CascadeType.ALL})
    private List<User> users;

    @OneToMany(mappedBy = "company", fetch=FetchType.LAZY, orphanRemoval=true, cascade = {CascadeType.ALL})
    private List<EntitySchema> entitySchemas;

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

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<EntitySchema> getEntitySchemas() {
        return entitySchemas;
    }

    public void setEntitySchemas(List<EntitySchema> entitySchemas) {
        this.entitySchemas = entitySchemas;
    }
}
