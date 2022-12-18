import org.jetbrains.annotations.NotNull;

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

        boolean[] left = getLeftPart(block);
        boolean[] right = getRightPart(block);
        boolean[] initKey = Arrays.copyOfRange(key, 0, 48);

        //ещё 15 раундов
        cryptoRound(left, right, initKey);

        block = FP(concat(right, left));

        return block;
    }

    // Конечная перестановка, обратная начальной перестановке.
    private boolean[] FP(boolean[] block) {
        return block;
    }

    // Применение xor к двум массивам битов одинаковой длины.
    private boolean[] xorBlocks(boolean[] block1, boolean[] block2) {
        assert block1.length != block2.length : "Блоки должны быть равной длины.";

        boolean[] result = new boolean[block1.length];

        for (int i = 0; i < block1.length; i++) {
            result[i] = block1[i] ^ block2[i];
        }

        return result;
    }

    // Выполнение первоначальной перестановки.
    private boolean[] IP(boolean[] inp) {
        assert inp.length == 64 : "Длина блока для перестановки должна быть 64 бита.";

        boolean[] result = new boolean[64];
        for (int i = 0; i < 8; i++) {
            result[i] = inp[58 - i * 8];
        }
        for (int i = 8; i < 16; i++) {
            result[i] = inp[60 - (i - 8) * 8];
        }
        for (int i = 16; i < 24; i++) {
            result[i] = inp[62 - (i - 8 * 2) * 8];
        }
        for (int i = 24; i < 32; i++) {
            result[i] = inp[62 - (i - 8 * 3) * 8];
        }
        for (int i = 32; i < 40; i++) {
            result[i] = inp[57 - (i - 8 * 4) * 8];
        }
        for (int i = 40; i < 48; i++) {
            result[i] = inp[59 - (i - 8 * 5) * 8];
        }
        for (int i = 48; i < 56; i++) {
            result[i] = inp[61 - (i - 8 * 6) * 8];
        }
        for (int i = 56; i < 64; i++) {
            result[i] = inp[63 - (i - 8 * 7) * 8];
        }
        return result;
    }

    // Разбиение блока на n частей.
    private ArrayList<boolean[]> splitBlockIntoParts(boolean[] block, int n) {
        assert block.length % 2 == 0 : "Длина блока должна быть чётной.";
        assert n % 2 == 0 : "Количество частей должно быть чётным.";
        assert n <= block.length : "Количество частей не должно превышать длину блока.";

        ArrayList<boolean[]> resultList = new ArrayList<>();
        int length = block.length / n;

        for (int i = 0; i < n; i++) {
            resultList.add(Arrays.copyOfRange(block, i * length, i * length + length));
        }


        return resultList;
    }

    // Получить левую часть блока.
    private boolean[] getLeftPart(boolean[] b) {
        return splitBlockIntoParts(b, 2).get(0);
    }

    // Получить правую часть блока.
    private boolean[] getRightPart(boolean[] b) {
        return splitBlockIntoParts(b, 2).get(1);
    }

    // Выполнение 1-го раунда шифроваия блока
    private void cryptoRound(boolean [] left, boolean[] right, boolean[] rKey) {
        assert left.length == 32 : "Длина левого блока на входе должна быть 32 бита.";
        assert right.length == 32 : "Длина правого блока на входе должна быть 32 бита.";
        assert rKey.length == 48 : "Длина ключа раунда должна быть 48 бит.";

        boolean[] tempLeft = right.clone();
        right = xorBlocks(left, feistelFunc(right, rKey));
        left = tempLeft;

    }

    // Применение функции Фейстеля
    private boolean[] feistelFunc(boolean[] block, boolean[] key) {
        assert block.length == 32 : "Длина блока в функции Фейстеля должна быть 32 бита.";
        assert key.length == 48 : "Длина ключа в функции Фейстеля должна быть 48 бит.";

        boolean[] result = new boolean[32];


        return result;
    }

    // Конкатенация двух булевых массивов в 1 результирующий массив
    private boolean[] concat(boolean[] arr1, boolean[] arr2) {
        boolean[] result = Arrays.copyOf(arr1, arr1.length + arr2.length);
        System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
        return result;
    }


}
