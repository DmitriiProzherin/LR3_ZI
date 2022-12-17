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

}
