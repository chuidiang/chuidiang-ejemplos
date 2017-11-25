package com.chuidiang.booteventbusrestangular.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by chuidiang on 25/11/17.
 */
@Controller
class MainWebController {
    @RequestMapping("/")
    String index (Model model) {
        'index'
    }
}
