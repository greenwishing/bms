package cn.greenwishing.bms.utils;

import java.util.UUID;

/**
 * @author Wu Fan
 */
public class GuidGenerator {

    public static String generate() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(generate());
        }
    }
}
