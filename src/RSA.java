import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RSA {

    private final static long MINIMUM_PRIME_RANGE = (long) 1e8;
    private final static long MAXIMUM_PRIME_RANGE = (long) 1e9;

    private long p, q;

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

    public ArrayList<BigInteger[]> generate(){
        ArrayList<BigInteger[]> key_pars= new ArrayList<>();

        this.p = generateRandomPrimeNumber();
        this.q = generateRandomPrimeNumber();


        BigInteger n = BigInteger.valueOf(p * q);
        BigInteger euler_f_n = BigInteger.valueOf((p - 1) * (q-1));
        BigInteger e = calculate_e(euler_f_n);
        BigInteger d = get_d(e, euler_f_n);

        key_pars.add(new BigInteger[] {e, n});
        key_pars.add(new BigInteger[] {d, n});

        System.out.println("Open key: " + Arrays.toString(key_pars.get(0)));
        System.out.println("Secret key: " + Arrays.toString(key_pars.get(1)));


        return key_pars;
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
