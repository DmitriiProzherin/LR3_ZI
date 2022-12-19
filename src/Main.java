import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        String strKey = "0001001100110100010101110111100110011011101111001101111111110001";
        String messageBlock = "0000000100100011010001010110011110001001101010111100110111101111";
        String expectedEncryptedBlock = "1000010111101000000100110101010000001111000010101011010000000101";


        boolean[] b_message_block = new boolean[64];
        for (int i = 0; i < strKey.length(); i++) {
            b_message_block[i] = (messageBlock.charAt(i) == '1');
        }


        SecretKey key = new SecretKey(strKey);
        DES des = new DES();



        des.encryptBlock(b_message_block, key);
        System.out.println(expectedEncryptedBlock);

       // des.decrypt(text, key);


    }
}