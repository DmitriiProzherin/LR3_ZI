
public class Main {
    public static void main(String[] args) {
        PlainText text = new PlainText("abcdefgh");
        SecretKey key = new SecretKey();
        DES des = new DES();

        des.encrypt(text, key);
        des.decrypt(text, key);


    }
}