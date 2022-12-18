import java.util.ArrayList;
import java.util.Arrays;

public class DES {

    public String encrypt(PlainText text, SecretKey key) {
        
        return null;
    }

    public String decrypt(PlainText text, SecretKey key) {

        return null;
    }
    
    
    private String[] splitToBlocks(String str) {
        int blocksAmount = str.length() / 8;
        int addedAmount = str.length() - blocksAmount * 8;
        String[] result = new String[blocksAmount];
        
        return null;
    }


    private boolean[] encryptBlock(boolean[] block, boolean[] key) {
        block = IP(block);

        boolean[] left = Utility.getLeftPart(block);
        boolean[] right = Utility.getRightPart(block);
        boolean[] initKey = Arrays.copyOfRange(key, 0, 48);

        //ещё 15 раундов
        cryptoRound(left, right, initKey);

        block = FP(Utility.concat(right, left));

        return block;
    }

    // Конечная перестановка, обратная начальной перестановке.
    private boolean[] FP(boolean[] inp) {
        assert inp.length == 64 : "Длина блока для перестановки должна быть 64 бита.";
        byte[] permVector = new byte[] {
                40,  8, 48, 16, 56, 24, 64, 32,
                39,  7, 47, 15, 55, 23, 63, 31,
                38,  6, 46, 14, 54, 22, 62, 30,
                37,  5, 45, 13, 53, 21, 61, 29,
                36,  4, 44, 12, 52, 20, 60, 28,
                35,  3, 43, 11, 51, 19, 59, 27,
                34,  2, 42, 10, 50, 18, 58, 26,
                33,  1, 41,  9, 49, 17, 57, 25
        };
        return Utility.mix(inp, 64, permVector);
    }

    // Выполнение первоначальной перестановки.
    private boolean[] IP(boolean[] inp) {
        assert inp.length == 64 : "Длина блока для перестановки должна быть 64 бита.";

        byte[] permVector = new byte[] {
                58, 50, 42, 34, 26, 18, 10, 2,
                60, 52, 44, 36, 28, 20, 12, 4,
                62, 54, 46, 38, 30, 22, 14, 6,
                64, 56, 48, 40, 32, 24, 16, 8,
                57, 49, 41, 33, 25, 17,  9, 1,
                59, 51, 43, 35, 27, 19, 11, 3,
                61, 53, 45, 37, 29, 21, 13, 5,
                63, 55, 47, 39, 31, 23, 15, 7
        };

        return Utility.mix(inp, 64, permVector);
    }

    // Выполнение одного раунда шифрования блока
    private void cryptoRound(boolean [] left, boolean[] right, boolean[] rKey) {
        assert left.length == 32 : "Длина левого блока на входе должна быть 32 бита.";
        assert right.length == 32 : "Длина правого блока на входе должна быть 32 бита.";
        assert rKey.length == 48 : "Длина ключа раунда должна быть 48 бит.";

        boolean[] tempLeft = right.clone();
        right = Utility.xorBlocks(left, feistelFunc(right, rKey));
        left = tempLeft;

    }

    // Применение функции Фейстеля.
    private boolean[] feistelFunc(boolean[] block, boolean[] key) {
        assert block.length == 32 : "Длина блока в функции Фейстеля должна быть 32 бита.";
        assert key.length == 48 : "Длина ключа в функции Фейстеля должна быть 48 бит.";

        boolean[] extendedBlock = Utility.xorBlocks(ext(block), key);
        boolean[] shrinkedBlock = sTransform(extendedBlock);

        return permutation(shrinkedBlock);
    }

    // Перестановка в функции Фейстеля.
    private boolean[] permutation(boolean[] resBlock) {
        return resBlock;
    }

    // Применение функции S-трансформации к расширенному блоку.
    private boolean[] sTransform(boolean[] extendedBlock) {
        return new boolean[0];
    }

    // Расширение блока до размера в 48 бит.
    private boolean[] ext(boolean[] booleans) {
        assert booleans.length == 32 : "Длина блока для расширения должна быть 32 бита";
        boolean[] result = new boolean[48];

        return result;
    }




}
