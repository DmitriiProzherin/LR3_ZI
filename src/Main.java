import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        String strKey = "0001001100110100010101110111100110011011101111001101111111110001";
        String messageBlock = "0000000100100011010001010110011110001001101010111100110111101111";

        boolean[] b_key = new boolean[64];
        boolean[] b_message_block = new boolean[64];

        for (int i = 0; i < strKey.length(); i++) {
            b_key[i] = (strKey.charAt(i) == '1');
            b_message_block[i] = (messageBlock.charAt(i) == '1');
        }


      //  PlainText text = new PlainText("abcdefgh");
        SecretKey key = new SecretKey(b_key);
        DES des = new DES();

      //  key.print_56_bit_key();
        //key.printKeysArr();

//        boolean[] test_bool = new boolean[] {true, false, true, false, true, false, true};
//
//        Utilities.Utility.shiftLeft(test_bool, 0);
//
//        System.out.println(Arrays.toString(test_bool));

//        System.out.println(Arrays.toString(b_key));
//        System.out.println(Arrays.toString(key.initTransform(b_key)));

        des.encryptBlock(b_message_block, key);


       // des.decrypt(text, key);

//        boolean[] test = new boolean[] {true, false, true, false, false, true};
//        ArrayList<boolean[]> testList = des.splitBlockIntoParts(test, 2);
//        testList.forEach(m -> System.out.println(Arrays.toString(m)));


    }
}