public class Input {

    byte[] inputBlock = new byte[64];

    Input(byte[] inputBlock) {
        this.inputBlock = IP(inputBlock);
    }

    private byte[] IP(byte[] inp) {
        byte[] result = new byte[64];
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

    public byte[] getLeft(){
        byte[] result = new byte[32];
        System.arraycopy(this.inputBlock, 0, result, 0, 32);
        return result;
    }


    public byte[] getRight(){
        byte[] result = new byte[32];
        System.arraycopy(this.inputBlock, 32, result, 0, 32);
        return result;
    }

    public byte[] getInputBlock() {
        return inputBlock;
    }
}
