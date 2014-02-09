package com.chuidiang.ejemplos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class BackupMySQL {

   public static void main(String[] args) {
      backup();
      restore();
   }

   private static void backup() {
      try {
         Process p = Runtime
               .getRuntime()
               .exec("C:/Aplicaciones/wamp/bin/mysql/mysql5.1.36/bin/mysqldump -u root -ppassword database");

         new HiloLector(p.getErrorStream()).start();
         
         InputStream is = p.getInputStream();
         FileOutputStream fos = new FileOutputStream("backup_pruebas.sql");
         byte[] buffer = new byte[1000];

         int leido = is.read(buffer);
         while (leido > 0) {
            fos.write(buffer, 0, leido);
            leido = is.read(buffer);
         }

         fos.close();

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private static void restore() {
      try {
         Process p = Runtime
               .getRuntime()
               .exec("C:/Aplicaciones/wamp/bin/mysql/mysql5.1.36/bin/mysql -u root -ppassword database");

         new HiloLector(p.getErrorStream()).start();
         
         OutputStream os = p.getOutputStream();
         FileInputStream fis = new FileInputStream("backup_pruebas.sql");
         byte[] buffer = new byte[1000];

         int leido = fis.read(buffer);
         while (leido > 0) {
            os.write(buffer, 0, leido);
            leido = fis.read(buffer);
         }

         os.flush();
         os.close();
         fis.close();

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   static class HiloLector extends Thread {
      private InputStream is;

      public HiloLector(InputStream is) {
         this.is = is;
      }

      @Override
      public void run() {
         try {
            byte[] buffer = new byte[1000];
            int leido = is.read(buffer);
            while (leido > 0) {
               String texto = new String(buffer, 0, leido);
               System.out.print(texto);
               leido = is.read(buffer);
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }

}
