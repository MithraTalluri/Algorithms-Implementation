class RabinKarp{
    private String pattern;
    private String text;
    private int k = 1000000007;
    private int M; // M -> pattern length
    private int N; // N -> text length

    //double hashing to avoid collisions.

    private long[] powPrime1;
    private long[] powPrime2;
    
    private int prime1;
    private int prime2;

    private long textHash1;
    private long textHash2;

    private long patternHash1;
    private long patternHash2;

    RabinKarp(int prime1, int prime2){
        this.prime1 = prime1;
        this.prime2 = prime2;

        this.powPrime1 = new long[10001]; 
        this.powPrime2 = new long[10001]; 

        //compute power array
        this.computepowPrimes(prime1, prime2, 10000);
    }
    
    public int search(String textString, String patternString){
        pattern = patternString;
        text = textString;
        M = pattern.length();
        N = text.length();

        //compute pattern hash
        long[] patternHashes = this.calculatepatternHashes(pattern, M, k);
        patternHash1 = patternHashes[0];
        patternHash2 = patternHashes[1];
    
        return searchPattern();
    }

    private void computepowPrimes(int prime1, int prime2, int M){
        powPrime1[0] = 1;
        powPrime2[0] = 1;
        for(int i = 1; i <= M; i++){
            powPrime1[i] = (long)(powPrime1[i - 1] * prime1) % k;
            powPrime2[i] = (long)(powPrime2[i - 1] * prime2) % k;
        }
    }

    private long[] calculatepatternHashes(String pattern, int M, int k){
        long patternHash1 = 0;
        long patternHash2 = 0;
        for(int i = 0; i < M; i++){
            patternHash1 = (patternHash1 + ((long)(pattern.charAt(i) * powPrime1[M - i]) % k) ) % k;
            patternHash2 = (patternHash2 + ((long)(pattern.charAt(i) * powPrime2[M - i]) % k) ) % k;
        }
        return new long[]{patternHash1, patternHash2};
    }

    private long[] initializetextHashes(String text, int M, int k){
        long textHash1 = 0;
        long textHash2 = 0;
        for(int i = 0; i < M; i++){
            textHash1 = (textHash1 + ((long)(text.charAt(i) * powPrime1[M - i]) % k) ) % k; 
            textHash2 = (textHash2 + ((long)(text.charAt(i) * powPrime2[M - i]) % k) ) % k; 
        }
        return new long[]{textHash1, textHash2};
    }

    private long[] calculatetextHashes(long textHash1, long textHash2, String text, int i, int M, int prime1, int prime2, int k){
        long prev1 = (long)(text.charAt(i - M) * powPrime1[M]);
        long prev2 = (long)(text.charAt(i - M) * powPrime2[M]);
        textHash1 = ((long)((textHash1 -  prev1 + text.charAt(i) ) * prime1 ) % k + k ) % k;
        textHash2 = (((textHash2 -  prev2 + text.charAt(i) ) * prime2 ) % k + k ) % k;
        return new long[]{textHash1, textHash2};
    }

    private int searchPattern(){
        int count = 0;
        long[] textHashes = initializetextHashes(text, M, k);
        textHash1 = textHashes[0];
        textHash2 = textHashes[1];
        if(textHash1 == patternHash1 && textHash2 == patternHash2){
            count++;
        }
        for(int i = M; i < N; i++){
            long[] hashes = calculatetextHashes(textHash1, textHash2, text, i, M, prime1, prime2, k);
            textHash1 = hashes[0];
            textHash2 = hashes[1];
            if(textHash1 == patternHash1 && textHash2 == patternHash2){
                count++;
            }
        }
        return count;
    }
}

public class RabinKarpAlgorithmImplementation{
    public static void main(String[] args){
        RabinKarp rk = new RabinKarp(97, 103);
        int count = rk.search("tetextexltexxettex", "tex");
        System.out.println("COUNT = " + count);
    }
}