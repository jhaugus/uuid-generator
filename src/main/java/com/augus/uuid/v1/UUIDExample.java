package com.augus.uuid.v1;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

import java.util.UUID;

public class UUIDExample {
    public static void main(String[] args) {
        TimeBasedGenerator timeBasedGenerator = Generators.timeBasedGenerator();
        UUID uuid1 = timeBasedGenerator.generate();
        System.out.println("Version 1 UUID: " + uuid1.toString());
    }
}