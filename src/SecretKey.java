import java.util.ArrayList;

public class SecretKey {

    boolean[] key_56_bit  = new boolean[56];
    boolean[][] keyList = new boolean[16][48];


    SecretKey(boolean[] initKey) {
        key_56_bit = initTransform(initKey);
        ArrayList<boolean[]> key_28_bit_arr = Utility.splitBlockIntoParts(key_56_bit, 2);



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
        assert in.length == 48 : "Длина ключа для финальной перестановки должна быть 48 бит.";

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



}
