/**
 * @author fjabellan 05/02/2024
 */
module module.impl1 {
     requires module.core;
     provides com.chuidiang.examples.modules.interfaces.IfzModule with com.chuidiang.examples.modules.impl1.Impl1;
}