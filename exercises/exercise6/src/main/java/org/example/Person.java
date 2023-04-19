package org.example;

import javax.crypto.*;
import java.security.*;
import java.util.Base64;

public class Person {

    private class Message{
        Person sender;
        Person receiver;
        byte[] message;
        byte [] signature;
        EncryptionType encryptionType;
        SignType signType;
        boolean isEncrypted;
        boolean isSigned;

        public Message(Person sender, Person receiver, byte[] message, byte[] signature,
                       EncryptionType encryptionType, SignType signType, boolean isEncrypted,
                       boolean isSigned) {
            this.sender = sender;
            this.receiver = receiver;
            this.message = message;
            this.signature = signature;
            this.encryptionType = encryptionType;
            this.signType = signType;
            this.isEncrypted = isEncrypted;
            this.isSigned = isSigned;
        }
    }
    private String name;
    private EncryptionType encryptionType;
    private SignType signType;
    private Message lastReceivedMsg;
    private String symmetricKey;
    private KeyPair asymmetricKeyPair;
    public static final int AES_KEY_SIZE = 256;

    public Person(String name) {
        this.name = name;
        this.encryptionType = EncryptionType.SYMMETRIC_AES_GCM;
        this.signType = SignType.SHA256_RSA;
        try {
            this.generateSymmetricKey();
            this.generateAsymmetricKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEncryptionType(EncryptionType encryptionType) {
        this.encryptionType = encryptionType;
    }

    public void setSignType(SignType signType) {
        this.signType = signType;
    }

    private String getName() {
        return this.name;
    }

    public void sendSignedMessage(Person receiver, String message, SignType signType){
        byte[] signature = signType.sign(message, this.getPrivateKey());
        receiver.receiveSignedMessage(this, message, signature, signType);
        receiver.receiveSignedMessage(this, message+"amrita", signature, signType);

    }

    public void receiveMessage(Message message){
        this.lastReceivedMsg = message;
    }

    public void sendMessage(Person receiver, String message, boolean encrypt, boolean signed){
        Message msg = null;
        if(encrypt){
            msg = new Message(this, receiver, sendMessage(receiver, message, this.encryptionType),
                    null, this.encryptionType, null, true, false);
        }
        if(signed){
            if(msg != null){
                msg.signature = this.signType.sign(message, this.getPrivateKey());
                msg.signType = this.signType;
                msg.isSigned = true;
            }else{
                msg = new Message(this, receiver, null,
                        this.signType.sign(message, this.getPrivateKey()), null,
                        this.signType, false, true);
            }
        }
        receiver.receiveMessage(msg);
    }

    public void receiveSignedMessage(Person sender, String message, byte[] signature, SignType signType) {
//        lastReceivedMsg = new Message(sender, this, message.getBytes(), signature, null,
//                signType, false, true);
        boolean isCorrect = signType.verifySignature(new String(message),
                signature, sender.getPublicKey());
        System.out.println(this.name + " Received signed Message from " + sender.getName()
                + " message: " + new String(message) + " with signature: "
                + new String(signature) + " is verified: " + isCorrect + ", Signed with: "
                + signType.toString());
    }

    public void receiveMessage(Person sender, byte[] encryptMessage, EncryptionType encryptionType) {
//        lastReceivedMsg = new Message(sender, this, encryptMessage, null, encryptionType, null,
//                true, false);
        String key= null;
        switch(encryptionType) {
            case SYMMETRIC_AES:
            case SYMMETRIC_AES_GCM:
                key = this.getSymmetricKey();
                break;
            case ASYMMETRIC_RSA:
                key = this.getPrivateKey();
                break;
            default:
                System.out.println("Invalid Encryption Type");
                return;
        }
        String decryptedMessage = encryptionType.decrypt(encryptMessage, key);
        System.out.println(this.name + " Received encrypted Message from " + sender.getName()
                + " message: " + new String(encryptMessage) + " encryption algorithm: "
                + encryptionType.toString());
        System.out.println(this.name + " Received decrypted Message from " + sender.getName()
                + " message: " + decryptedMessage);
    }

    public byte[] sendMessage(Person receiver, String message, EncryptionType encryptionType) {
        String key= null;
        switch(encryptionType) {
            case SYMMETRIC_AES:
            case SYMMETRIC_AES_GCM:
                key = receiver.getSymmetricKey();
                break;
            case ASYMMETRIC_RSA:
                key = receiver.getPublicKey();
                break;
            default:
                System.out.println("Invalid Encryption Type");
                return null;
        }
        byte[] encryptedMsg = encryptionType.encrypt(message, key);
        receiver.receiveMessage(this, encryptedMsg, encryptionType);
        return encryptedMsg;
    }

    public void readLastMessage(){
        if(lastReceivedMsg.isEncrypted){
            String key= null;
            switch(lastReceivedMsg.encryptionType) {
                case SYMMETRIC_AES:
                case SYMMETRIC_AES_GCM:
                    key = this.getSymmetricKey();
                    break;
                case ASYMMETRIC_RSA:
                    key = this.getPrivateKey();
                    break;
                default:
                    System.out.println("Invalid Encryption Type");
                    return;
            }
            String decryptedMessage = lastReceivedMsg.encryptionType.decrypt(lastReceivedMsg.message, key);
            System.out.println(this.name + " Received encrypted Message from " + lastReceivedMsg.sender.getName()
                    + " message: " + new String(lastReceivedMsg.message) + " encryption algorithm: "
                    + lastReceivedMsg.encryptionType.toString());
            System.out.println(this.name + " Received decrypted Message from " + lastReceivedMsg.sender.getName()
                    + " message: " + decryptedMessage);
        }
        if(lastReceivedMsg.isSigned){
            boolean isCorrect = lastReceivedMsg.signType.verifySignature(new String(lastReceivedMsg.message),
                    lastReceivedMsg.signature, lastReceivedMsg.sender.getPublicKey());
            System.out.println(this.name + " Received signed Message from " + lastReceivedMsg.sender.getName()
                    + " message: " + new String(lastReceivedMsg.message) + " with signature "
                    + new String(lastReceivedMsg.signature) + " is verified: " + isCorrect + " Signed with: "
                    + lastReceivedMsg.signType.toString());
        }
    }

    private void generateSymmetricKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(AES_KEY_SIZE);

        // Generate Key
        SecretKey key = keyGenerator.generateKey();
        this.symmetricKey = new String(Base64.getEncoder().encode(key.getEncoded()));
    }

    public String getSymmetricKey() {
        return symmetricKey;
    }

    public void generateAsymmetricKey() {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(2048);
            this.asymmetricKeyPair = keyPairGen.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPublicKey(){
       return new String(Base64.getEncoder().encode(this.asymmetricKeyPair.getPublic().getEncoded()));
    }

    private String getPrivateKey(){
        return new String(Base64.getEncoder().encode(this.asymmetricKeyPair.getPrivate().getEncoded()));
    }

}
