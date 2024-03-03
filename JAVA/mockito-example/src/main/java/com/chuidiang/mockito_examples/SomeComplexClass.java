package com.chuidiang.mockito_examples;

/**
 * Para ejemplo de test con mockito.
 * Esta clase simula ser una clase compleja que depende de clases que trabajan con base de datos, con
 * una red ethernet y que saca otra clase para enviar los resultados.
 * @author chuidiang
 * @date 15/11/2020
 */
public class SomeComplexClass {
    private DataBaseClass dataBaseClass;
    private NetworkClass networkClass;
    private OutputClass outputClass;

    public SomeComplexClass(DataBaseClass dataBaseClass, NetworkClass networkClass, OutputClass outputClass){
        this.dataBaseClass = dataBaseClass;
        this.networkClass = networkClass;
        this.outputClass = outputClass;
    }

    public void concatStringsFromDataBaseAndNetworkAndOutputResult(){
        try {
            final String stringFromDataBase = dataBaseClass.getStringFromDataBase();
            final String stringFromRemoteServer = networkClass.getStringFromRemoteServer();
            outputClass.printOutput(stringFromDataBase+" - "+stringFromRemoteServer);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
