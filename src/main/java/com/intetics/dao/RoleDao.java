package com.intetics.dao;

import com.intetics.bean.Role;

import java.util.List;

/**
 * Operates over {@link com.intetics.bean.Role}
 */

public interface RoleDao {
    Role getRoleByName(String roleName);

    List<Role> getRoleNamesExcludingAdmin();
}
