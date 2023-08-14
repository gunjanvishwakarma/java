package com.gunjan.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;

public class ProcessDemo {

   public static void main(String[] args) {
      try {
         // create a new process
         System.out.println("Creating Process...");
         Process p = Runtime.getRuntime().exec("C:\\New folder\\test.bat");
   
         CompletableFuture.runAsync(() -> {
      
            try(BufferedReader es = new BufferedReader(new InputStreamReader(p.getErrorStream())))
            {
               String line = "";
               while((line = es.readLine()) != null)
               {
                  System.out.println("Error => "+ line);
               }
            }
            catch(IOException e)
            {
               e.printStackTrace();
            }
         });
   
         CompletableFuture.runAsync(() -> {
            try(BufferedReader is = new BufferedReader(new InputStreamReader(p.getInputStream())))
            {
               String line = "";
               while((line = is.readLine()) != null)
               {
                  System.out.println("Output => "+ line);
               }
            }
            catch(IOException e)
            {
               e.printStackTrace();
            }
         });

         // cause this process to stop until process p is terminated
         int i = p.waitFor();

         // when you manually close notepad.exe program will continue here
         System.out.println("Waiting over. " + i);
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }
}