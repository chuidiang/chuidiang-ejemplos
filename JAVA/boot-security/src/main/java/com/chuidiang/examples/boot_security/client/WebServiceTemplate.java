package com.chuidiang.examples.boot_security.client;

import com.chuidiang.examples.boot_security.server.Permissions;
import com.chuidiang.examples.boot_security.server.Roles;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/double/")
public class WebServiceTemplate {

    private static final String CAN_BORRAR="hasRole('" +
            Roles.ROL_ADMIN +
            "') or hasAuthority('" +
            Permissions.PERMISSION_BORRAR +
            "')";

    private static final String CAN_CREAR="hasRole('" +
            Roles.ROL_ADMIN +
            "') or hasAuthority('" +
            Permissions.PERMISSION_CREAR +
            "')";

    @PreAuthorize(CAN_CREAR)
    @RequestMapping("crear")
    public double crear(){
        return Math.random();
    }

    @PreAuthorize(CAN_BORRAR)
    @RequestMapping("borrar")
    public double borrar(){
        return Math.random();
    }

    @PreAuthorize("hasRole('admin')")
    @RequestMapping("destruyeTodo")
    public double destruyeTodo(){
        return Math.random();
    }

}
