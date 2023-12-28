package org.example.atbconfigure.util;

public class CommonUtils {
    public static void printArray(byte[] array){
        for (Byte b : array) {
            System.out.print(" " + b);
        }
        System.out.println();
    }
    public static int crc8(byte[] data) {
        int crc = 0xFF;

        for (byte el : data) {
            crc ^= el;

            for (int i = 0; i < 8; i++) {
                if ((crc & 0x80) != 0) {
                    crc = (crc << 1) ^ 0x31;
                } else {
                    crc = crc << 1;
                }
            }
        }

        return crc;
    }
    public static boolean isCRCValid(byte[] array){
        System.out.println("isCRCValid");
        printArray(array);
        byte[] body = new byte[array.length -1];
        for(int i = 0; i < array.length -1; i++){
            body[i] = array[i];
        }
        byte crc = (byte) crc8(body);
        boolean valid = crc == array[array.length-1];
        System.out.println("isCRCValid" + valid + " | " +crc);
        return valid;
    }
}
