package org.example;

public class Main {
    public static void main(String[] args) {
        /*
            Create two object of person class
            alice and bob to send and receive messages
         */
        Person alice = new Person("Alice");
        Person bob = new Person("Bob");
        alice.sendMessage(bob, "Hello bob, how are you", EncryptionType.SYMMETRIC_AES_GCM);
        bob.sendMessage(alice, "I am doing good", EncryptionType.SYMMETRIC_AES_GCM);
        alice.sendMessage(bob, "Let's meet up", EncryptionType.SYMMETRIC_AES);
        bob.sendMessage(alice, "Sure", EncryptionType.SYMMETRIC_AES);
        alice.sendMessage(bob, "Welcome to California", EncryptionType.ASYMMETRIC_RSA);
        bob.sendSignedMessage(alice, "Catch up", SignType.SHA256_RSA);


 //       alice.sendMessage(bob, "Hi there!", true, false);
 //       bob.readLastMessage();
//        bob.setEncryptionType(EncryptionType.SYMMETRIC_AES);
//        bob.sendMessage(alice, "Hello, Alice!", true, false);
//        alice.readLastMessage();
    }
}