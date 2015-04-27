package com.intetics.dao;

import com.intetics.bean.Role;

import java.util.List;

/**
 * todo[a.chervyakovsky] place meaningful javadoc here
 */

public interface RoleDao {
    Role getRoleByName(String roleName);

    List<Role> getRoleNamesExcludingAdmin();
}
