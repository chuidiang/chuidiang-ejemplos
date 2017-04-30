package com.chuidiang.examples.test

/**
 * Created by JAVIER on 29/04/2017.
 */
class UserAlradyExistsException extends Exception {

    UserAlradyExistsException(String userName){
        super ("$userName already exists")
    }
}
