package com.augus.uuid.v5;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class UUIDExample {

    public static UUID generateUUID5(UUID namespace, String name) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(namespace.toString().getBytes(StandardCharsets.UTF_8));
        md.update(name.getBytes(StandardCharsets.UTF_8));
        byte[] hash = md.digest();
        
        hash[6] &= 0x0f;  // clear version
        hash[6] |= 0x50;  // set to version 5
        hash[8] &= 0x3f;  // clear variant
        hash[8] |= 0x80;  // set to IETF variant
        
        // Only use the first 16 bytes of SHA-1 hash
        byte[] truncatedHash = new byte[16];
        System.arraycopy(hash, 0, truncatedHash, 0, 16);
        
        return UUID.nameUUIDFromBytes(truncatedHash);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        UUID namespace = UUID.randomUUID();  // or any fixed namespace UUID
        String name = "example.com";
        UUID uuid5 = generateUUID5(namespace, name);
        System.out.println("Version 5 UUID: " + uuid5.toString());
    }
}
