package com.intetics.pojos;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * created: 01.04.2015 10:21
 *
 * @author a.chervyakovsky
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
    private Set<User> users = new HashSet<User>();

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<EntityName> getEntityNames() {
        return entityNames;
    }

    public void setEntityNames(Set<EntityName> entityNames) {
        this.entityNames = entityNames;
    }

    public Byte[] getLogo() {
        return logo;
    }

    public void setLogo(Byte[] logo) {
        this.logo = logo;
    }

}
