/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bitcompromise;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
 
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
 
public class AES {
  String IV = "AAAAAAAAAAAAAAAA";
  
  public AES(){
  }
  
  
  public static void main(String [] args) {
      
    try {
      AES aes = new AES();
 
      byte[] cipher = aes.encrypt("test text 123al", "0123456789abcdef");
 
      System.out.print("cipher:  ");
      for (int i=0; i<cipher.length; i++)
        System.out.print((int) cipher[i]+" ");
      System.out.println("");
 
      String decrypted = aes.decrypt(cipher, "0123456789abcdef");
 
      System.out.println("decrypt: " + decrypted);
 
    } catch (Exception e) {
      e.printStackTrace();
    } 
    
  }
 
  public byte[] encrypt(String plainText, String encryptionKey) throws Exception {
      
    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
    cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
    return cipher.doFinal(plainText.getBytes("UTF-8"));
    
  }
  
  public byte[] encrypt(byte[] plainText, byte[] encryptionKey) throws Exception {
      System.out.println("Ciphering");
    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
    SecretKeySpec key = new SecretKeySpec(encryptionKey, "AES");
    cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
    return cipher.doFinal(plainText);
  }
 
  public String decrypt(byte[] cipherText, String encryptionKey) throws Exception{
    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
    cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
    return new String(cipher.doFinal(cipherText),"UTF-8");
  }
  
  public byte[] decrypt(byte[] cipherText, byte[] encryptionKey) throws Exception{
      System.out.println("Decrypting");
    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
    SecretKeySpec key = new SecretKeySpec(encryptionKey, "AES");
    cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
    return cipher.doFinal(cipherText);
  }
}
