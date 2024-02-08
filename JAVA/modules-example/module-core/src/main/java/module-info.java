/**
 * @author fjabellan 05/02/2024
 */
module module.core {
    exports com.chuidiang.examples.modules.interfaces;
    provides com.chuidiang.examples.modules.interfaces.IfzModule with com.chuidiang.examples.modules.implementation.CoreImplementation;
}