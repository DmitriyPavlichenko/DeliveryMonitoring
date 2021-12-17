package com.delmon.deliverymonitoring.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.delmon.deliverymonitoring.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    WAREHOUSE_WORKER(Sets.newHashSet(DEPARTMENT_FIND,
                                    EMPLOYEE_FIND,
                                    PRODUCT_FIND,
                                    ORDER_FIND, ORDER_SAVE, ORDER_DELETE)
    ),
    PRODUCT_SUPPLIER(Sets.newHashSet(DEPARTMENT_FIND,
                                    EMPLOYEE_FIND,
                                    ORDER_FIND,
                                    PRODUCT_FIND, PRODUCT_SAVE, PRODUCT_DELETE)
    ),
    MANAGER(Sets.newHashSet(DEPARTMENT_FIND, DEPARTMENT_SAVE, DEPARTMENT_DELETE,
                            EMPLOYEE_SAVE, EMPLOYEE_FIND, EMPLOYEE_DELETE)
    ),
    ADMINISTRATOR(Sets.newHashSet(ApplicationUserPermission.values()));


    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }
}
