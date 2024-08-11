package com.augus.uuid.v4;

import java.util.UUID;

public class UUIDVersion4 {
    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        System.out.println("Version 4 UUID: " + uuid.toString());
    }
}
