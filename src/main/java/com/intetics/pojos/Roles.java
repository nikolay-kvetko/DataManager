package com.intetics.pojos;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * created: 01.04.2015 12:17
 *
 * @author a.chervyakovsky
 */

@Entity
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "role", fetch=FetchType.LAZY, orphanRemoval=true, cascade = {CascadeType.ALL})
    private Set<User> users = new HashSet<User>();

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
