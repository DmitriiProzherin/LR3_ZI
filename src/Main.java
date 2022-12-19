import java.util.Arrays;
import java.util.Scanner;

import static Utilities.Utility.*;
import static java.lang.System.in;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {


        String strKey = "0001001100110100010101110111100110011011101111001101111111110001";
      //  String messageBlock = "0000000100100011010001010110011110001001101010111100110111101111";
      //  String expectedEncryptedBlock = "1000010111101000000100110101010000001111000010101011010000000101";
        String test = "aaaak", encryptedString, decryptedString;


        SecretKey key = new SecretKey(strKey);
        DES des = new DES();

        encryptedString = des.encrypt(test, key);
        decryptedString = des.decrypt(encryptedString, key);

        out.print("Inp: ");
        printBoolArray(byteToBoolArr(strToByteArr(test)));
        out.println("Enc: " + encryptedString);
        out.println("Dec: " + decryptedString);

    }
}