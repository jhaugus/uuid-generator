package com.augus.uuid.v3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class UUIDExample {

    public static UUID generateUUID3(UUID namespace, String name) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(namespace.toString().getBytes(StandardCharsets.UTF_8));
        md.update(name.getBytes(StandardCharsets.UTF_8));
        byte[] hash = md.digest();
        
        hash[6] &= 0x0f;  // clear version
        hash[6] |= 0x30;  // set to version 3
        hash[8] &= 0x3f;  // clear variant
        hash[8] |= 0x80;  // set to IETF variant
        
        return UUID.nameUUIDFromBytes(hash);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        UUID namespace = UUID.randomUUID();  // or any fixed namespace UUID
        String name = "example.com";
        UUID uuid3 = generateUUID3(namespace, name);
        System.out.println("Version 3 UUID: " + uuid3.toString());
    }
}
