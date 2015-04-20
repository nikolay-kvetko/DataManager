package com.intetics.dao;

import com.intetics.bean.Role;

/**
 * todo[a.chervyakovsky] place meaningful javadoc here
 */

public interface RoleDao {
    Role getRoleByName(String roleName);
}
