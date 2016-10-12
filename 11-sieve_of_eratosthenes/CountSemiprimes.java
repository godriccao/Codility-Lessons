// https://codility.com/demo/results/trainingJGFDNK-GBU/

// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int[] solution(int N, int[] P, int[] Q) {
        int M = P.length;
        int[] minPrimes = new int[N + 1]; // initiallized with 0 by Java
        int[] res = new int[M];
        
        // Create the min prime factors array from 0 to N. tc: O(N * log log N)
        for(int i = 2; i * i <= N; i++) {
            if(minPrimes[i] == 0) {
                for(int j = i * i; j <= N; j++) {
                    if(minPrimes[j] == 0 && j % i == 0) { 
                        minPrimes[j] = i; 
                    }
                }
            }
        }
        //System.out.println(Arrays.toString(minPrimes));
        
        // Create a prefix sum array to store how many numbers we have from N = 1 until now
        int[] pref = new int[N+1];
        pref[0] = 0;
        pref[1] = 0;
        
        // tc: O(N)
        int sum = 0;
        for(int i = 2; i <= N; i++){
           if(minPrimes[i] != 0 && // have at least two factors
               minPrimes[i / minPrimes[i]] == 0) {// have exactly two factors
                sum++;
            }
            pref[i] = sum;
        }
        
        // Find result. tc: O(M)
        for(int i = 0; i < M; i++) {
            res[i] = pref[Q[i]] - pref[P[i] - 1];
        }
        return res;
    }
}
