package com.chuidiang.ejemplos.encrypt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Ejemplo sencillo de encriptado/desencriptado con algoritmo RSA. Se comenta
 * tambien como guardar las claves en fichero y recuperarlas después.
 * 
 * @author Chuidiang
 */
public class RSAAsymetricCrypto {
   private static Cipher rsa;

   public static void main(String[] args) throws Exception {
      // Generar el par de claves
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      KeyPair keyPair = keyPairGenerator.generateKeyPair();
      PublicKey publicKey = keyPair.getPublic();
      PrivateKey privateKey = keyPair.getPrivate();

      // Se salva y recupera de fichero la clave publica
      saveKey(publicKey, "publickey.dat");
      publicKey = loadPublicKey("publickey.dat");

      // Se salva y recupera de fichero la clave privada
      saveKey(privateKey, "privatekey.dat");
      privateKey = loadPrivateKey("privatekey.dat");

      // Obtener la clase para encriptar/desencriptar
      rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");

      // Texto a encriptar
      String text = "Text to encrypt";

      // Se encripta
      rsa.init(Cipher.ENCRYPT_MODE, publicKey);
      byte[] encriptado = rsa.doFinal(text.getBytes());

      // Escribimos el encriptado para verlo, con caracteres visibles
      for (byte b : encriptado) {
         System.out.print(Integer.toHexString(0xFF & b));
      }
      System.out.println();

      // Se desencripta
      rsa.init(Cipher.DECRYPT_MODE, privateKey);
      byte[] bytesDesencriptados = rsa.doFinal(encriptado);
      String textoDesencripado = new String(bytesDesencriptados);

      // Se escribe el texto desencriptado
      System.out.println(textoDesencripado);

   }

   private static PublicKey loadPublicKey(String fileName) throws Exception {
      FileInputStream fis = new FileInputStream(fileName);
      int numBtyes = fis.available();
      byte[] bytes = new byte[numBtyes];
      fis.read(bytes);
      fis.close();

      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      KeySpec keySpec = new X509EncodedKeySpec(bytes);
      PublicKey keyFromBytes = keyFactory.generatePublic(keySpec);
      return keyFromBytes;
   }

   private static PrivateKey loadPrivateKey(String fileName) throws Exception {
      FileInputStream fis = new FileInputStream(fileName);
      int numBtyes = fis.available();
      byte[] bytes = new byte[numBtyes];
      fis.read(bytes);
      fis.close();

      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      KeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
      PrivateKey keyFromBytes = keyFactory.generatePrivate(keySpec);
      return keyFromBytes;
   }

   private static void saveKey(Key key, String fileName) throws Exception {
      byte[] publicKeyBytes = key.getEncoded();
      FileOutputStream fos = new FileOutputStream(fileName);
      fos.write(publicKeyBytes);
      fos.close();
   }
}
