package com.delmon.deliverymonitoring.security.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ApplicationUserPermission {
    DEPARTMENT_SAVE("department:save"), DEPARTMENT_FIND("department:find"), DEPARTMENT_DELETE("department:delete"),

    EMPLOYEE_SAVE("employee:save"), EMPLOYEE_FIND("employee:find"), EMPLOYEE_DELETE("employee:delete"),

    ORDER_SAVE("order:save"), ORDER_FIND("order:find"), ORDER_DELETE("order:delete"),

    PRODUCT_SAVE("product:save"), PRODUCT_FIND("product:find"), PRODUCT_DELETE("product:delete"),

    REGISTRATION_REGISTER("registration:register"), REGISTRATION_DELETE("registration:delete");


    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public static List<GrantedAuthority> getAllPermissions() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Arrays.stream(ApplicationUserPermission.values())
                .map(p -> new SimpleGrantedAuthority(p.getPermission()))
                .forEach(authorities::add);

        return authorities;
    }
}
