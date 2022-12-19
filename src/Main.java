import static Utilities.Utility.*;

public class Main {
    public static void main(String[] args) {

        String strKey = "0001001100110100010101110111100110011011101111001101111111110001";
        String messageBlock = "0000000100100011010001010110011110001001101010111100110111101111";
        String expectedEncryptedBlock = "1000010111101000000100110101010000001111000010101011010000000101";



      //  PlainText text = new PlainText("abcdefgh");
        SecretKey key = new SecretKey(strKey);
        DES des = new DES();

        System.out.println("\nШифровка блока:\n".toUpperCase());

        System.out.println("Блок для шифровки:\n" + messageBlock);
        System.out.println("Результат шифрования блока:");
        printBoolArray(des.encryptBlock(messageBlock, key));
        System.out.println("Ожидаемый результат:\n" + expectedEncryptedBlock);

        System.out.println("\nДешифровка блока:\n".toUpperCase());
        System.out.println("Блок для дешифровки:\n" + expectedEncryptedBlock);
        System.out.println("Результат дешифрования блока:");
        printBoolArray(des.decryptBlock(expectedEncryptedBlock, key));
        System.out.println("Ожидаемый результат:\n" + messageBlock);

       // des.decrypt(text, key);


    }
}