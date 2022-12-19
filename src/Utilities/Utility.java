package Utilities;

import java.util.ArrayList;
import java.util.Arrays;

public class Utility {

    public static byte[] strToByteArr(String str) {
        StringBuilder resStrB = new StringBuilder();
        byte[] result = new byte[str.length() * 16];

        for (int i = 0; i < str.length(); i++) {
            String s = Integer.toBinaryString(str.charAt(i));
            resStrB.append("0".repeat(Math.max(0, 16 - s.length())));
            resStrB.append(s);
        }
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) Character.getNumericValue(resStrB.charAt(i));
        }

        return result;
    }

    public static boolean[] byteToBoolArr(byte[] in) {
        boolean[] result = new boolean[in.length];

        for (int i = 0; i < in.length; i++) {
            assert in[i] == 1 || in[i] == 0 : "Перевод возможен только для массива из 0 и 1.";
            result[i] = (in[i] == 1);
        }
        return result;
    }

    // Перемешивание элементов в массиве согласно данной матрице.
    public static boolean[] mix(boolean[] inputArr, int resLength, byte[] mixArr) {
        boolean[] result = new boolean[resLength];

        for (int i = 0; i < resLength; i++) {
            result[i] = inputArr[mixArr[i] - 1];
        }
        return result;


    }

    // Разбиение блока на n частей.
    public static ArrayList<boolean[]> splitBlockIntoParts(boolean[] block, int n) {
       // assert block.length % 2 == 0 : "Длина блока должна быть чётной.";
       // assert n % 2 == 0 : "Количество частей должно быть чётным.";
        assert n <= block.length : "Количество частей не должно превышать длину блока.";

        ArrayList<boolean[]> resultList = new ArrayList<>();
        int length = block.length / n;

        for (int i = 0; i < n; i++) {
            resultList.add(Arrays.copyOfRange(block, i * length, i * length + length));
        }


        return resultList;
    }

    // Конкатенация двух булевых массивов в 1 результирующий массив.
    public static boolean[] concat(boolean[] arr1, boolean[] arr2) {

        if (arr1 == null) return arr2;
        else if (arr2 == null) return arr1;

        boolean[] result = Arrays.copyOf(arr1, arr1.length + arr2.length);
        System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
        return result;
    }

    // Выделение левой части блока.
    public static boolean[] getLeftPart(boolean[] b) {
        return Utility.splitBlockIntoParts(b, 2).get(0);
    }

    // Выделение правой части блока.
    public static boolean[] getRightPart(boolean[] b) {
        return Utility.splitBlockIntoParts(b, 2).get(1);
    }

    // Применение xor к двум массивам битов одинаковой длины.
    public static boolean[] xor(boolean[] block1, boolean[] block2) {
        assert block1.length == block2.length : "Блоки должны быть равной длины.";

        boolean[] result = new boolean[block1.length];

        for (int i = 0; i < block1.length; i++) {
            result[i] = block1[i] ^ block2[i];
        }

        return result;
    }

    // Выполняет циклический сдвиг введённого массива влево на n позиций.
    public static void shiftLeft(boolean[] booleans, int n) {
        int shiftLength = n % booleans.length;
        boolean[] temp = booleans.clone();

        System.arraycopy(temp, shiftLength, booleans, 0, booleans.length - shiftLength);
        System.arraycopy(temp, 0, booleans, booleans.length - shiftLength, booleans.length - (booleans.length - shiftLength));
    }


    public static String[] splitToBlocks(String str) {
        int blocksAmount = str.length() / 8;
        int addedAmount = str.length() - blocksAmount * 8;
        String[] result = new String[blocksAmount];

        return null;
    }

    public static void printBoolArray(boolean[] arr) {
        for (boolean b : arr) {
            if (b) System.out.print("1");
            else System.out.print("0");
        }
        System.out.println();
    }

    public static boolean[] binaryStringToBoolArr(String str) {
        boolean[] result = new boolean[str.length()];

        for (int i = 0; i < result.length; i++){
            result[i] = (str.charAt(i) == '1');
        }
        return result;

    }

    public static boolean[] invertArray(boolean[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            boolean temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        return array;
    }

    public static String boolArrToString(boolean[] arr){
        StringBuilder r = new StringBuilder();
        for (boolean b : arr) {
            if (b) r.append("1");
            else r.append("0");
        }
        return r.toString();
    }


}
