package com.chuidiang.ejemplos.cxf;

import org.apache.cxf.configuration.jsse.TLSServerParameters;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.transport.http_jetty.JettyHTTPServerEngineFactory;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

/**
 * @author fjabellan
 * @date 18/09/2020
 */
public class Main {


    private static String KEY_STORE_PATH_NAME = "serverKey.jks";
    private static String KEY_STORE_PASSWORD = "changeit";
    private static String PRIVATE_KEY_PASSWORD = "changeit";

    public static void main(String[] args) throws Exception {
        coinfigureJettyTLs();

        JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
        svrFactory.setServiceClass(Calculadora.class);
        svrFactory.setAddress("https://localhost:9443/Calculadora");
        svrFactory.setServiceBean(new CalculadoraImpl());
        svrFactory.create();


    }

    public static void coinfigureJettyTLs() throws Exception {
        /*
         * create a JettyHTTPServerEngineFactory to handle the configuration of
         * network port numbers for use with "HTTPS"
         */
        JettyHTTPServerEngineFactory jettyHTTPServerEngineFactory = new JettyHTTPServerEngineFactory();

        // load the key store containing the server certificate
        File keyStoreFile = new File(KEY_STORE_PATH_NAME);
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(new FileInputStream(keyStoreFile),
                KEY_STORE_PASSWORD.toCharArray());

        // create a key manager to handle the server private/public key pair
        KeyManagerFactory keyManagerFactory = KeyManagerFactory
                .getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, PRIVATE_KEY_PASSWORD.toCharArray());
        KeyManager[] keyManager = keyManagerFactory.getKeyManagers();

        // set the TLSServerParameters on theJettyHTTPServerEngineFactory
        TLSServerParameters tLSServerParameters = new TLSServerParameters();
        tLSServerParameters.setKeyManagers(keyManager);
        jettyHTTPServerEngineFactory.setTLSServerParametersForPort(9443,
                tLSServerParameters);
    }

}
