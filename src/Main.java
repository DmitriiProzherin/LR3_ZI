import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;


import static Utilities.Utility.formatedBoolStringtoString;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) throws IOException {

        DES des = new DES();

        Path inputPath = Path.of("src/input.txt");
        Path keyPath = Path.of("src/key.txt");
        Path outputPath = Path.of("src/output.txt");

        String key = Files.readString(keyPath);
        String test = Files.readString(inputPath);
        String encryptedString, decryptedString;


        BufferedWriter writer = new BufferedWriter(new FileWriter("src/output.txt"));
        writer.write(des.encrypt(test, key));
        writer.close();


        encryptedString = Files.readString(outputPath);
        decryptedString = des.decrypt(encryptedString, key);



        out.println("Inp: " + test);
        out.println("Enc: " + formatedBoolStringtoString(encryptedString));
        out.println("Dec: " + decryptedString);

    }
}