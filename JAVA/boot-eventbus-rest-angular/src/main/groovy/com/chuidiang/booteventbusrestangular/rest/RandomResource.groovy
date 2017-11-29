package com.chuidiang.booteventbusrestangular.rest

import com.chuidiang.booteventbusrestangular.logic.RandomNumber
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by chuidiang on 25/11/17.
 */
@RestController
@RequestMapping("/random")
class RandomResource {
    @RequestMapping(method=RequestMethod.GET)
    RandomNumber get(){
        new RandomNumber (value:Math.random())
    }
}
