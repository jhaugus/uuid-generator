package com.augus.uuid.v2;

import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.UUID;

public class UUIDVersion2 {

    public static UUID generateUUID2(int localDomain, long posixId) throws Exception {
        long timestamp = System.currentTimeMillis() * 10000L + 0x01B21DD213814000L;
        // localDomain is typically 0, 1, or 2 (for user, group, or organization)
        timestamp = (posixId & 0xFFFFFFFFL) | (timestamp << 32);

        long clockSeq = new SecureRandom().nextLong() & 0x3FFF; // 14 bits
        byte[] node = getHardwareAddress();

        // Clear the version and set it to 2
        timestamp &= ~(0xF000L << 48);
        timestamp |= 2L << 60;

        // Combine to generate UUID
        long mostSigBits = timestamp;
        long leastSigBits = (clockSeq << 48) | toLong(node);

        return new UUID(mostSigBits, leastSigBits);
    }

    private static byte[] getHardwareAddress() throws Exception {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();
            byte[] hardwareAddress = ni.getHardwareAddress();
            if (hardwareAddress != null) {
                return hardwareAddress;
            }
        }
        throw new IllegalStateException("No hardware address found");
    }

    private static long toLong(byte[] bytes) {
        long value = 0;
        for (int i = 0; i < bytes.length; i++) {
            value = (value << 8) | (bytes[i] & 0xFF);
        }
        return value;
    }

    public static void main(String[] args) {
        try {
            UUID uuid2 = generateUUID2(0, 12345); // Example UID/GID
            System.out.println("Version 2 UUID: " + uuid2.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
