// https://codility.com/demo/results/trainingAAW7SS-C4R/
// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int[] A) {
        int N = A.length;
        int K = 0;
        
        // Find possible maximum K. tc: O(Sqrt(N))
        while(K * (K - 1) < N){
            K++;
        }
        K--;
        
        int[] nextPeak = new int[N];
        int cur = 0;
        
        // Create nextPeak array. tc: O(N), sc: O(N)
        for(int i = 1; i < N - 1; i++){
            if(A[i-1] < A[i] && A[i] > A[i+1]) {
                while(cur <= i){
                    nextPeak[cur] = i;
                    cur++;
                }
            }
        }
        while(cur < N){
            nextPeak[cur] = -1;
            cur++;
        }
        
        if(nextPeak[0] == -1){
            return 0;
        }
        
        // System.out.println(Arrays.toString(nextPeak));
        
        // Find K. tc: O(K) * O(K) = O(Sqrt(N)) * O(Sqrt(N)) = O(N)
        while(K != 0){
            int i = 0;
            int count = 0;
            while(i < N && nextPeak[i] != -1) {
                if(nextPeak[i] == i){
                    i += K;
                } else {
                    i = nextPeak[i] + K;
                }
                count++;
                if(count >= K) {
                    return K;
                }
            }
            K--;
        }
        
        return 0;
    }
}
