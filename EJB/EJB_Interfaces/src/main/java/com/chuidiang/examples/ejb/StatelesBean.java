package com.chuidiang.examples.ejb;

import javax.ejb.Remote;

@Remote
public interface StatelesBean {
   String sayHello();
}
