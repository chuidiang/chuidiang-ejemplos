package com.chuidiang.examples.test

/**
 * Created by JAVIER on 29/04/2017.
 */
class UserManagment {
    IfzDataBase dataBase

    def addUser (String name, String password, String rePassword) throws UserAlradyExistsException, PasswordMismatchException{
        assert name       // not null nor empty
        assert password
        if (dataBase.userExists(name)){
            throw new UserAlradyExistsException(name)
        }
        if (password!=rePassword){
            throw new PasswordMismatchException()
        }

        dataBase.addUser(name,password)
    }
}
