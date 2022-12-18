import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        PlainText text = new PlainText("abcdefgh");
        SecretKey key = new SecretKey();
        DES des = new DES();

        des.encrypt(text, key);
        des.decrypt(text, key);

//        boolean[] test = new boolean[] {true, false, true, false, false, true};
//        ArrayList<boolean[]> testList = des.splitBlockIntoParts(test, 2);
//        testList.forEach(m -> System.out.println(Arrays.toString(m)));


    }
}