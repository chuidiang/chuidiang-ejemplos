package com.chuidiang.mockito_examples;

/**
 * @author fjabellan
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
