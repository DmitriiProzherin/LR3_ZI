import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {

        String strKey = "0001001100110100010101110111100110011011101111001101111111110001";
        String test = "Я написал шифратор и дешифратор!", encryptedString, decryptedString;

        DES des = new DES();

        encryptedString = des.encrypt(test, strKey);
        decryptedString = des.decrypt(encryptedString, strKey);

        out.println("Inp: " + test);
        out.println("Enc: " + encryptedString);
        out.println("Dec: " + decryptedString);

    }
}