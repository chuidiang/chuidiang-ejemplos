package com.chuidiang.examples

import com.chuidiang.examples.test.IfzDataBase
import com.chuidiang.examples.test.PasswordMismatchException
import com.chuidiang.examples.test.UserAlradyExistsException
import com.chuidiang.examples.test.UserManagment
import spock.lang.Specification

/**
 * Created by JAVIER on 29/04/2017.
 */
class UserManagmentTest extends Specification{
    IfzDataBase dataBase = Mock() {
        userExists("Juan") >> true
    }
    UserManagment userMangment = new UserManagment(dataBase: dataBase)

    def "add valid user"(){
        when:
            userMangment.addUser("Pedro","secret","secret")

        then:
            1*dataBase.addUser("Pedro","secret")
    }

    def "add user with empty name"() {
        when:
            userMangment.addUser("", "secret","secret")

        then:
            thrown(Throwable.class)
            0*dataBase.addUser(_,_)
    }

    def "add user with wrong password repetition"(){
        when:
            userMangment.addUser("Pedro","secret", "password")

        then:
            thrown(PasswordMismatchException.class)
            0*dataBase.addUser(_,_)
    }

    def "reject already existing user"() {
        when:
            userMangment.addUser ("Juan", "password", "password")

        then:
            thrown(UserAlradyExistsException.class)
            0*dataBase.addUser(_,_)
    }
}
