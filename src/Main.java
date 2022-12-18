import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        boolean[] b_key = new boolean[] {
            true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true
        };

        PlainText text = new PlainText("abcdefgh");
        SecretKey key = new SecretKey(b_key);
        DES des = new DES();

        key.print_56_bit_key();
        key.printKeysArr();

//        boolean[] test_bool = new boolean[] {true, false, true, false, true, false, true};
//
//        Utility.shiftLeft(test_bool, 0);
//
//        System.out.println(Arrays.toString(test_bool));

//        System.out.println(Arrays.toString(b_key));
//        System.out.println(Arrays.toString(key.initTransform(b_key)));

       // des.encrypt(text, key);
       // des.decrypt(text, key);

//        boolean[] test = new boolean[] {true, false, true, false, false, true};
//        ArrayList<boolean[]> testList = des.splitBlockIntoParts(test, 2);
//        testList.forEach(m -> System.out.println(Arrays.toString(m)));


    }
}