package com.chuidiang.examples.boot_security.bootsecurity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebService {
    @RequestMapping("/crear")
    public double crear(){
        return Math.random();
    }

    @PreAuthorize("hasRole('admin') or hasAuthority('borrar')")
    @RequestMapping("/borrar")
    public double borrar(){
        return Math.random();
    }

    @PreAuthorize("hasRole('admin')")
    @RequestMapping("/destruyeTodo")
    public double destruyeTodo(){
        return Math.random();
    }

}
