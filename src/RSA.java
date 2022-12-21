import java.util.Random;

public class RSA {

    private final static long MINIMUM_PRIME_RANGE = (long) 1e4;
    private final static long MAXIMUM_PRIME_RANGE = (long) 1e8;

    private long p, q;

    private int[] random_p_q(){
        this.p = generateRandomPrimeNumber();
        this.q = generateRandomPrimeNumber();

        return null;
    }


    private long generateRandomPrimeNumber(){
        Random r = new Random();
        long k = r.nextLong(MINIMUM_PRIME_RANGE, MAXIMUM_PRIME_RANGE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
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



}
