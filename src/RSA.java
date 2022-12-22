import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RSA {

    private final static long MINIMUM_PRIME_RANGE = (long) 1e8;
    private final static long MAXIMUM_PRIME_RANGE = (long) 1e9;

    private long max_block_length;

    private long generateRandomPrimeNumber(){
        Random r = new Random();
        long k = r.nextLong(MINIMUM_PRIME_RANGE, MAXIMUM_PRIME_RANGE);
        do {
            k++;
        } while (!isPrime(k));
        return k;
    }

    private boolean isPrime(long n){
        if (n % 2 == 0) return false;
        for (long i = 3; i < n; i+=2) {
            if ((n % i) == 0) return false;
        }
        return true;
    }

    public ArrayList<BigInteger[]> generateKeys(){
        ArrayList<BigInteger[]> key_pairs= new ArrayList<>();

        long p = generateRandomPrimeNumber();
        long q = generateRandomPrimeNumber();


        BigInteger n = BigInteger.valueOf(p * q);
        BigInteger euler_f_n = BigInteger.valueOf((p - 1) * (q -1));
        BigInteger e = calculate_e(euler_f_n);
        BigInteger d = get_d(e, euler_f_n);

        key_pairs.add(new BigInteger[] {e, n});
        key_pairs.add(new BigInteger[] {d, n});

        try {
            BufferedWriter open_key_writer = new BufferedWriter(new FileWriter("src/public_key_pair"));
            open_key_writer.write(Arrays.toString(key_pairs.get(0)));
            open_key_writer.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        try {
            BufferedWriter private_key_writer = new BufferedWriter(new FileWriter("src/private_key_pair"));
            private_key_writer.write(Arrays.toString(key_pairs.get(1)));
            private_key_writer.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


        System.out.println("Public key pair: " + Arrays.toString(key_pairs.get(0)));
        System.out.println("Private key pair :" + Arrays.toString(key_pairs.get(1)));


        return key_pairs;
    }

    private BigInteger get_d(BigInteger e, BigInteger f) {
        BigInteger d, k = new BigInteger("1");
        while (!f.multiply(k).add(BigInteger.ONE).mod(e).equals(BigInteger.ZERO)) {
            k = k.add(BigInteger.ONE);
        }
        d = f.multiply(k).add(BigInteger.ONE).divide(e);
        assert f.min(d).signum() == 1;
        return d;
    }

    private boolean isCoprime(BigInteger a, BigInteger b){
        BigInteger gcd = a.gcd(b);
        return gcd.equals(BigInteger.ONE);
    }

    private BigInteger calculate_e(BigInteger euler_f_n){
        BigInteger res;
        int n = 0;

        do {
            n++;
            res = BigInteger.valueOf( (long) Math.pow(2, n) + 1);
        }
        while (!isCoprime(res, euler_f_n));

        return res;
    }


}
