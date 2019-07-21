package com.chuidiang.examples.boot_security.ws;

import com.chuidiang.examples.boot_security.bootsecurity.Permissions;
import com.chuidiang.examples.boot_security.bootsecurity.Roles;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController
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
