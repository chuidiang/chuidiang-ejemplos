package com.chuidiang.examples.boot_security.bootsecurity;

import com.chuidiang.examples.boot_security.ws.WebServiceTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mi/double/")
public class WebService extends WebServiceTemplate {
    @Override
    public double borrar() {
        return super.borrar();
    }

    @Override
    public double crear() {
        return super.crear();
    }
}
