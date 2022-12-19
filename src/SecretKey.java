import Utilities.Utility;
import static Utilities.Utility.*;

import java.util.ArrayList;

public class SecretKey {

    private final boolean[][] keysArr = new boolean[16][48];
    private final boolean[] key_56_bit;

    private final boolean[] init_64_key;
    private boolean[][] cBlocks = new boolean[16][28];
    private boolean[][] dBlocks = new boolean[16][28];



    SecretKey(boolean[] initKey) {
        assert initKey.length == 64 : "длина ключа должна быть в 64 бита";

        this.init_64_key = initKey;
        key_56_bit = initTransform(initKey);


        ArrayList<boolean[]> key_28_bit_arr = Utility.splitBlockIntoParts(key_56_bit, 2);
        Utility.shiftLeft(key_28_bit_arr.get(0), shiftLength(0));
        Utility.shiftLeft(key_28_bit_arr.get(1), shiftLength(0));
        cBlocks[0] = key_28_bit_arr.get(0);
        dBlocks[0] = key_28_bit_arr.get(1);

        for (int i = 1; i < 16; i++) {
            cBlocks[i] = cBlocks[i - 1].clone();
            dBlocks[i] = dBlocks[i - 1].clone();
            Utility.shiftLeft(cBlocks[i], shiftLength(i));
            Utility.shiftLeft(dBlocks[i], shiftLength(i));
        }

        for (int i = 0; i < 16; i++) { keysArr[i] = finalTransform(Utility.concat(cBlocks[i], dBlocks[i]));}

    }

    SecretKey(String initKey) {
        assert initKey.length() == 64 : "длина ключа должна быть в 64 бита";

        this.init_64_key = strToBoolArr(initKey);
        key_56_bit = initTransform(strToBoolArr(initKey));

        ArrayList<boolean[]> key_28_bit_arr = Utility.splitBlockIntoParts(key_56_bit, 2);
        Utility.shiftLeft(key_28_bit_arr.get(0), shiftLength(0));
        Utility.shiftLeft(key_28_bit_arr.get(1), shiftLength(0));
        cBlocks[0] = key_28_bit_arr.get(0);
        dBlocks[0] = key_28_bit_arr.get(1);

        for (int i = 1; i < 16; i++) {
            cBlocks[i] = cBlocks[i - 1].clone();
            dBlocks[i] = dBlocks[i - 1].clone();
            Utility.shiftLeft(cBlocks[i], shiftLength(i));
            Utility.shiftLeft(dBlocks[i], shiftLength(i));
        }

        for (int i = 0; i < 16; i++) { keysArr[i] = finalTransform(Utility.concat(cBlocks[i], dBlocks[i]));}

    }

    private boolean[] initTransform(boolean[] in) {
        assert in.length == 64 : "Длина входного ключа для должна быть 64 бита.";

        byte[] mixVector = new byte[] {
                57, 49, 41, 33, 25, 17,  9,
                 1, 58, 50, 42, 34, 26, 18,
                10,  2, 59, 51, 43, 35, 27,
                19, 11,  3, 60, 52, 44, 36,
                63, 55, 47, 39, 31, 23, 15,
                 7, 62, 54, 46, 38, 30, 22,
                14,  6, 61, 53, 45, 37, 29,
                21, 13,  5, 28, 20, 12,  4
        };

        return Utility.mix(in, 56, mixVector);
    }

    private boolean[] finalTransform(boolean[] in) {
        assert in.length == 56 : "Длина ключа для финальной перестановки должна быть 56 бит.";

        byte[] mixVector = new byte[] {
                14, 17, 11, 24,  1,  5,  3, 28,
                15,  6, 21, 10, 23, 19, 12,  4,
                26,  8, 16,  7, 27, 20, 13,  2,
                41, 52, 31, 37, 47, 55, 30, 40,
                51, 45, 33, 48, 44, 49, 39, 56,
                34, 53, 46, 42, 50, 36, 29, 32
        };

        return Utility.mix(in, 48, mixVector);

    }

    private int shiftLength(int iterationNumber) {
        assert iterationNumber >= 0 && iterationNumber <= 15;

        return switch (iterationNumber) {
            case 0, 1, 8, 15 -> 1;
            case 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14 -> 2;
            default -> -1;
        };
    }

    public boolean[][] getKeysArr() {
        return keysArr;
    }

    public void printKeysArr() {
        for (int i = 0; i < 16; i++) {
            System.out.println("\n" + i + "-ый ключ. Длина равна 48 бит." );
            for (int j = 0; j < 48; j++) {
                if (keysArr[i][j]) System.out.print("1");
                else System.out.print("0");
            }
            System.out.println();
        }
    }

    public void print_56_bit_key() {
        System.out.println("56-битный ключ после перестановки исходного ключа");
        for (int i = 0; i < 56; i++) {
            if (key_56_bit[i]) System.out.print("1");
            else System.out.print("0");
        }
        System.out.println();
    }

    public boolean[] getInit_64_key() {
        return init_64_key;
    }

}
