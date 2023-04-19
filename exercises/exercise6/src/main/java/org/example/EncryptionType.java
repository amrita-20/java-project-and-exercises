package org.example;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/*
    Create enum for setting encryption type
    Symmetric & Asymmetric
 */
public enum EncryptionType {
    SYMMETRIC_AES_GCM("AES/GCM/NoPadding", "AES"){
        static int GCM_TAG_LENGTH = 16;
        static int GCM_SALT_LENGTH = 12;
        static byte[] salt;
        static {
            salt = new byte[GCM_SALT_LENGTH];
            SecureRandom random = new SecureRandom();
            random.nextBytes(salt);
        }
        @Override
        public byte [] encrypt(String message, String key){
            byte[] decodedKey = Base64.getDecoder().decode(key);
            return this.encrypt(message, new SecretKeySpec(decodedKey, 0, decodedKey.length, this.KEY_ALGO));
        }
        private byte[] encrypt(String message, SecretKey key) {
            byte[] encryptedText = new byte[0];
            try {
                Cipher cipher = Cipher.getInstance(this.ENCRYPT_ALGO);
                cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(GCM_TAG_LENGTH*8, salt));
                encryptedText = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return encryptedText;
        }

        @Override
        public String decrypt(byte[] encryptedMessage, String key){
            byte[] decodedKey = Base64.getDecoder().decode(key);
            return this.decrypt(encryptedMessage, new SecretKeySpec(decodedKey, 0, decodedKey.length, this.KEY_ALGO));
        }

        private String decrypt(byte[] encryptedMessage, SecretKey key) {
            byte[] message = new byte[0];
            try {
                Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
                cipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(GCM_TAG_LENGTH*8, salt));
                message = cipher.doFinal(encryptedMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new String(message, StandardCharsets.UTF_8);
        }
    },
    SYMMETRIC_AES("AES", "AES"){
        @Override
        public byte [] encrypt(String message, String key){
            byte[] decodedKey = Base64.getDecoder().decode(key);
            return this.encrypt(message, new SecretKeySpec(decodedKey, 0, decodedKey.length, this.KEY_ALGO));
        }
        private byte[] encrypt(String message, SecretKey key) {
            byte[] encryptedText = new byte[0];
            try {
                Cipher cipher = Cipher.getInstance(this.ENCRYPT_ALGO);
                cipher.init(Cipher.ENCRYPT_MODE, key);
                encryptedText = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return encryptedText;
        }

        @Override
        public String decrypt(byte[] encryptedMessage, String key){
            byte[] decodedKey = Base64.getDecoder().decode(key);
            return this.decrypt(encryptedMessage, new SecretKeySpec(decodedKey, 0, decodedKey.length, this.KEY_ALGO));
        }

        private String decrypt(byte[] encryptedMessage, SecretKey key) {
            byte[] message = new byte[0];
            try {
                Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
                cipher.init(Cipher.DECRYPT_MODE, key);
                message = cipher.doFinal(encryptedMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new String(message);
        }
    },
    ASYMMETRIC_RSA("RSA/ECB/PKCS1Padding", "RSA") {

        @Override
        public byte[] encrypt(String message, String key) {
            byte[] encryptedMessage = new byte[0];
            try {
                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key.getBytes()));
                KeyFactory keyFactory = KeyFactory.getInstance(this.KEY_ALGO);
                PublicKey publicKey = keyFactory.generatePublic(keySpec);
                Cipher cipher = Cipher.getInstance(this.ENCRYPT_ALGO);
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                encryptedMessage = cipher.doFinal(message.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return encryptedMessage;
        }

        @Override
        public String decrypt(byte[] encryptedMessage, String key) {
            String message = "";
            try {
                PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key.getBytes()));
                KeyFactory keyFactory = KeyFactory.getInstance(this.KEY_ALGO);
                PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
                Cipher cipher = Cipher.getInstance(this.ENCRYPT_ALGO);
                cipher.init(Cipher.DECRYPT_MODE, privateKey);
                message = new String(cipher.doFinal(encryptedMessage));

            } catch (Exception e) {
                e.printStackTrace();
            }
            return message;
        }
    };
    public final String ENCRYPT_ALGO;
    public final String KEY_ALGO;
    private EncryptionType(String algo, String keyAlgo){
        this.ENCRYPT_ALGO = algo;
        this.KEY_ALGO = keyAlgo;
    }
    //Create method to encrypt message using key
    public abstract byte[] encrypt(String message, String  key);

    //Create method to decrypt message using key
    public abstract String decrypt(byte[] encryptedMessage, String key);

}
