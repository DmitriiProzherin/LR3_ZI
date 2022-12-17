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


    private boolean[] encryptBlock(boolean[] block) {

        return null;
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

    private boolean[] IP(boolean[] inp) {
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



}
