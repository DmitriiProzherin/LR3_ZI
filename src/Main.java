import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String testBlock = "abcdefgh";

        SecretKey key = new SecretKey();
        DES des = new DES(key);

        des.encrypt(testBlock);
        des.decrypt(testBlock);



        Input input = new Input(Utility.stringToBinaryBytes(testBlock));

        System.out.println(Arrays.toString(input.getLeft()));
        System.out.println(Arrays.toString(input.getRight()));

    }
}