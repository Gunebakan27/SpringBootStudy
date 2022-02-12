package com.TechPro.SpringBootStudy.basic_authentication;

public enum ApplicationUserPermisson {
    STUDENT_READ("student:read"), STUDENT_WRITE("student:write");
private final String permission;
    ApplicationUserPermisson(String permission) {
        this.permission=permission;
    }
}
