// Score: 72. Some prime_length test cases failed.

// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int[] A) {
        int N = A.length;
        int[] nextPeak = new int[N];
        
        // Create nextPeak array. tc: O(N), sc: O(N)
        int cur = 0;
        int countPeak = 0;
        for(int i = 1; i < N - 1; i++) {
            if(A[i-1] < A[i] && A[i] > A[i+1]){
                countPeak++;
                while(cur <= i){
                    nextPeak[cur] = i;
                    cur++;
                }
            }
        }
        while(cur < N) {
            nextPeak[cur] = -1;
            cur++;
        }
        
        if(countPeak == 0) return 0;
        //System.out.println(Arrays.toString(nextPeak));
        //System.out.println(countPeak);
        
        // Find K. tc: O(K) * O(N/K) = O(N)
        int B = countPeak;
        int K = N % B == 0 ? N / B : N / B + 1;
        while(K <= N){
            int i = 0;
            int count = 0;
            while(i < N && nextPeak[i] != -1){
                if(nextPeak[i] < (count + 1) * K){
                    count++;
                    i = count * K;
                } else {
                    break;
                }
            }
            B = N % K == 0 ? N / K : N / K + 1;
            if(count == B){
                return B;
            }
            K++;
        }
        
        return 0;        
    }

}
