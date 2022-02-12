package com.TechPro.SpringBootStudy.basic_authentication;

import com.google.common.collect.Sets;


import java.util.Set;

import static com.TechPro.SpringBootStudy.basic_authentication.ApplicationUserPermisson.STUDENT_READ;
import static com.TechPro.SpringBootStudy.basic_authentication.ApplicationUserPermisson.STUDENT_WRITE;
import static org.assertj.core.util.Sets.newHashSet;

public enum ApplicationUserRoles { // sabit(fix) datalarin saklandigi yapidir

    STUDENT(Sets.newHashSet(STUDENT_READ)), ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE));

    private final Set<ApplicationUserPermisson> permissions;

    ApplicationUserRoles(Set<ApplicationUserPermisson> permissions) {
        this.permissions = permissions;
    }
}
