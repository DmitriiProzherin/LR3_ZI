import java.util.ArrayList;
import java.util.Arrays;

public class Utility {

    public static byte[] stringToBinaryBytes(String str) {
        StringBuilder resStrB = new StringBuilder();
        byte[] result = new byte[str.length() * 8];

        for (int i = 0; i < str.length(); i++) {
            String s = Integer.toBinaryString(str.charAt(i));
            resStrB.append("0".repeat(Math.max(0, 8 - s.length())));
            resStrB.append(s);
        }
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) Character.getNumericValue(resStrB.charAt(i));
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

    // Конкатенация двух булевых массивов в 1 результирующий массив.
    public static boolean[] concat(boolean[] arr1, boolean[] arr2) {
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
    public static boolean[] xorBlocks(boolean[] block1, boolean[] block2) {
        assert block1.length != block2.length : "Блоки должны быть равной длины.";

        boolean[] result = new boolean[block1.length];

        for (int i = 0; i < block1.length; i++) {
            result[i] = block1[i] ^ block2[i];
        }

        return result;
    }

}
