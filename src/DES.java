import Utilities.Utility;

import java.util.ArrayList;

import static Utilities.Utility.*;

public class DES {

    public String encrypt(PlainText text, SecretKey key) {





        return null;
    }

    // Шифровка одного блока.
    public boolean[] encryptBlock(boolean[] inputBlock, SecretKey key) {
        assert inputBlock.length == 64 : "Длина одного кодируемого блока равна 64 бита.";

        System.out.println("Массив из 16-ти ключей:");
        key.printKeysArr();


        System.out.println("\nИсходный блок:");
        for (boolean b : inputBlock) {
            if (b) System.out.print("1");
            else System.out.print("0");
        }
        System.out.println();

        inputBlock = IP(inputBlock);

        System.out.println("\nИсходный блок после IP:");
        for (boolean b : inputBlock) {
            if (b) System.out.print("1");
            else System.out.print("0");
        }
        System.out.println();

        boolean[][] left = new boolean[17][];
        boolean[][] right = new boolean[17][];


        left[0] = Utility.getLeftPart(inputBlock);
        System.out.println("\nL-0. Длина " + left[0].length + " бита.");
        for (boolean b : left[0]) {
            if (b) System.out.print("1");
            else System.out.print("0");
        }

        right[0] = Utility.getRightPart(inputBlock);
        System.out.println("\nR-0. Длина " + left[0].length + " бита.");
        for (boolean b : right[0]) {
            if (b) System.out.print("1");
            else System.out.print("0");
        }

        for (int i = 1; i<=16; i++) {
            left[i] = right[i-1].clone();
            System.out.println("\nL-" + i + ". Длина " + left[i].length + " бита.");
            for (boolean b : left[i]) {
                if (b) System.out.print("1");
                else System.out.print("0");
            }

            right[i] = xor(left[i - 1], feistel(right[i - 1], key.getKeysArr()[i - 1]));
            System.out.println("\nR-" + i + ". Длина " + right[i].length + " бита.");
            for (boolean b : right[i]) {
                if (b) System.out.print("1");
                else System.out.print("0");
            }
        }

        boolean result[] = FP(concat(right[16], left[16]));

        System.out.println("\n\nЗашифрованный блок:");
        printBoolArray(result);

        return result;
    }

    public String decrypt(PlainText text, SecretKey key) {

        return null;
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
        right = xor(left, feistel(right, rKey));
        left = tempLeft;

    }

    // Применение функции Фейстеля.
    private boolean[] feistel(boolean[] inBlock, boolean[] key) {
        assert inBlock.length == 32 : "Длина блока в функции Фейстеля должна быть 32 бита.";
        assert key.length == 48 : "Длина ключа в функции Фейстеля должна быть 48 бит.";

        System.out.println("\nФункция фейстеля.");

        System.out.println(inBlock.length + "-бит блок:");
        printBoolArray(inBlock);

        boolean[] e = ext(inBlock);
        System.out.println("\n" + e.length + "-бит расширение:");
        printBoolArray(e);

        System.out.println("\n"+key.length + "-бит ключ:");
        printBoolArray(key);

        boolean[] x = xor(e, key);
        System.out.println("\n48-бит xor расширения и ключа:");
        printBoolArray(x);

        ArrayList<boolean[]> blocks = splitBlockIntoParts(xor(e, key), 8);

        System.out.println("\n8 блоков по 6 бит:");
        blocks.forEach(Utility::printBoolArray);

        boolean[] res = null;
        System.out.println("\n8 блоков по 4 бита:");

        for (boolean[] b :
                blocks) {
            b = sTransform(b, blocks.indexOf(b));
            res = concat(res, b);
            printBoolArray(b);
        }

        System.out.println("\nСклеивание блоков по 4 бита. Длина: " + res.length);
        printBoolArray(res);


        System.out.println("\nПерестановка:");
        printBoolArray(P(res));

        return P(res);
    }

    // Перестановка в функции Фейстеля.
    private boolean[] P(boolean[] inp) {
        assert inp.length == 32 : "Длина блока для перестановки должна быть 32 бита.";

        byte[] permVector = new byte[] {
                16,  7,  20,  21,
                29,  12,  28,  17,
                1,  15,  23,  26,
                5,  18,  31,  10,
                2,   8,  24,  14,
                32,  27,  3,   9,
                19,  13,  30,   6,
                22,  11,   4,  25
        };

        return Utility.mix(inp, 32, permVector);
    }

    // Применение функции S-трансформации к расширенному блоку.
    private boolean[] sTransform(boolean[] block, int blockNo) {
        assert block.length == 6 : "Длина блока для S-преобразоания должна быть 6 бит";

        byte[][] sTable = getSTable(blockNo);

        int i = getRowIndex(block);
       // System.out.println("Индекс строки: " + i);
        int j = getColIndex(block);
     //   System.out.println("Индекс столбца: " + j);

        String str = Integer.toBinaryString(sTable[i][j]);
        boolean[] resultBlock = new boolean[4];

        int p = 3;
        for (int k = str.length() - 1; k >= 0; k--) {
            resultBlock[p] = str.charAt(k) == '1';
            p--;
        }
        return resultBlock;
    }

    private int getRowIndex(boolean[] block) {

        byte i, j;

        if (block[0]) i = 1;
        else i = 0;

        if (block[5]) j = 1;
        else j = 0;

        return i * 2 + j;
    }

    private int getColIndex(boolean[] block) {

        int p = 3, res = 0;

        for (int i = 1; i < 5; i++) {
            if (block[i]) {
                res += Math.pow(2, p);
            }
            p--;
        }

        return res;
    }



    // Расширение блока до размера в 48 бит.
    private boolean[] ext(boolean[] booleans) {
        assert booleans.length == 32 : "Длина блока для расширения должна быть 32 бита";

        byte[] mixVector = new byte[] {
                32,  1,  2,  3,  4,  5,
                 4,  5,  6,  7,  8,  9,
                 8,  9, 10, 11, 12, 13,
                12, 13, 14, 15, 16, 17,
                16, 17, 18, 19, 20, 21,
                20, 21, 22, 23, 24, 25,
                24, 25, 26, 27, 28, 29,
                28, 29, 30, 31, 32,  1
        };

        return Utility.mix(booleans, 48, mixVector);
    }


    private byte[][] getSTable(int i) {
        assert i>=0 && i<=7 : "Индексы таблиц от 0 до 7.";


        // 8 S-таблиц

            byte[][] s1 = new byte[][] {
                    {14,  4, 13,  1,  2, 15, 11,  8,  3, 10,  6, 12,  5,  9,  0,  7},
                    { 0, 15,  7,  4, 14,  2, 13,  1, 10,  6, 12, 11,  9,  5,  3,  8},
                    { 4,  1, 14,  8, 13,  6,  2, 11, 15, 12,  9,  7,  3, 10,  5,  0},
                    {15, 12,  8,  2,  4,  9,  1,  7,  5, 11,  3, 14, 10,  0,  6, 13}
            };

            byte[][] s2 = new byte[][] {
                    { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
                    { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
                    { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
                    { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 }
            };

            byte[][] s3 = new byte[][] {
                    { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
                    { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
                    { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
                    { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 }
            };

            byte[][] s4 = new byte[][] {
                    { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
                    { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
                    { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
                    { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 }
            };

            byte[][] s5 = new byte[][] {
                    { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
                    { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
                    { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
                    { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 }
            };

            byte[][] s6 = new byte[][] {
                    { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
                    { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
                    { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
                    { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 }
            };

            byte[][] s7 = new byte[][] {
                    { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
                    { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
                    { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
                    { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 }
            };

            byte[][] s8 = new byte[][] {
                    { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
                    { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
                    { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
                    { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 }
            };


        return switch (i) {
            case 0 -> s1;
            case 1 -> s2;
            case 2 -> s3;
            case 3 -> s4;
            case 4 -> s5;
            case 5 -> s6;
            case 6 -> s7;
            case 7 -> s8;
            default -> null;
        };
    }

}
