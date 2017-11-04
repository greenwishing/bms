package cn.greenwishing.bms.utils;

import java.util.UUID;

/**
 * @author Frank wu
 */
public class GuidGenerator {

    public static String generate() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        return uuidStr.replaceAll("-", "");
    }

    public static String generate(int size) {
        String guid = generate();
        if (guid.length() < size) {
            return guid;
        }
        return guid.substring(0, size);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(generate());
        }
    }
}
