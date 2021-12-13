package com.delmon.deliverymonitoring.security;

public enum ApplicationUserPermission {
    DEPARTMENT_SAVE("department:save"),
    DEPARTMENT_FIND("department:find"),
    DEPARTMENT_DELETE("department:delete"),

    EMPLOYEE_SAVE("employee:save"),
    EMPLOYEE_FIND("employee:find"),
    EMPLOYEE_DELETE("employee:delete"),

    ORDER_SAVE("order:save"),
    ORDER_FIND("order:find"),
    ORDER_DELETE("order:delete"),

    PRODUCT_SAVE("product:save"),
    PRODUCT_FIND("product:find"),
    PRODUCT_DELETE("product:delete"),

    REGISTRATION("registration:register");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
