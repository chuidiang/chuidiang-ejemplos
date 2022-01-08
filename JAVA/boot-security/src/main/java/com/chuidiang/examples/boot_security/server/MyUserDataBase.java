package com.chuidiang.examples.boot_security.server;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MyUserDataBase implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (name.equals(password)) {
            SimpleGrantedAuthority rolAdmin = new SimpleGrantedAuthority("ROLE_admin");
            SimpleGrantedAuthority rolUser = new SimpleGrantedAuthority("ROLE_user");
            SimpleGrantedAuthority permisoBorrar = new SimpleGrantedAuthority("borrar");
            SimpleGrantedAuthority permisoCrear = new SimpleGrantedAuthority("crear");
            ArrayList<GrantedAuthority> permisos = new ArrayList<>();
            if ("admin".equals(name)){
                permisos.add(rolAdmin);
            }
            if ("borrador".equals(name)) {
                permisos.add(rolUser);
                permisos.add(permisoBorrar);
            }
            if ("user".equals(name)){
                permisos.add(rolUser);
            }
            if ("creador".equals(name)){
                permisos.add(permisoCrear);
            }
            return new UsernamePasswordAuthenticationToken(name, password, permisos);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}
