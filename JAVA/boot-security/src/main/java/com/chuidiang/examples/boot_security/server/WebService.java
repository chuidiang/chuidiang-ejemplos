package com.chuidiang.examples.boot_security.server;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mi/double/")
public class WebService {

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
        System.out.println("Procedo a crear");
        return Math.random();
    }

    @PreAuthorize(CAN_BORRAR)
    @RequestMapping("borrar")
    public double borrar(){
        System.out.println("Procedo a borrar");
        return Math.random();
    }

    @PreAuthorize("hasRole('admin')")
    @RequestMapping("destruyeTodo")
    public double destruyeTodo(){
        System.out.println("Me lo cargo todo");
        return Math.random();
    }

}
