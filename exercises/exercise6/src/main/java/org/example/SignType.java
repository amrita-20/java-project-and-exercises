package org.example;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/*
    Created enum for setting sign type algorithm
 */
public enum SignType {
    SHA256_RSA("SHA256withRSA", "RSA"){
        @Override
        public byte[] sign(String message, String key) {
            byte[] signature = new byte[0];
            try {
                PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key.getBytes()));
                KeyFactory keyFactory = KeyFactory.getInstance(this.KEY_ALGO);
                PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
                Signature privateSignature = Signature.getInstance(this.SIGN_ALGO);
                privateSignature.initSign(privateKey);
                privateSignature.update(message.getBytes(StandardCharsets.UTF_8));
                signature = privateSignature.sign();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return signature;
        }

        @Override
        public boolean verifySignature(String message, byte[] signature, String key) {
            boolean isCorrect = false;
            try {
                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key.getBytes()));
                KeyFactory keyFactory = KeyFactory.getInstance(this.KEY_ALGO);
                PublicKey publicKey = keyFactory.generatePublic(keySpec);
                Signature publicSignature = Signature.getInstance(this.SIGN_ALGO);
                publicSignature.initVerify(publicKey);
                publicSignature.update(message.getBytes(StandardCharsets.UTF_8));
                isCorrect = publicSignature.verify(signature);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return isCorrect;
        }
    };

    public final String SIGN_ALGO;
    public final String KEY_ALGO;
    private SignType(String algo, String keyAlgo){
        this.SIGN_ALGO = algo;
        this.KEY_ALGO = keyAlgo;
    }

   //Created method for signing the data using private key
    public abstract byte[] sign(String message, String privateKey);

    //Created a method to verify signature using public key
    public abstract boolean verifySignature(String message, byte[] signature, String publicKey);
}
