package com.example.course.Interview.Apple;

import java.util.HashMap;

public class Solution6 {
    public int solution(int[] A, int[] B, int N){
        // find the pair that has the highest number of edge counts
        // use a hashmap to store the city as key, and number of edge that this city has as value
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<A.length;i++){
            int EdgeCountA = map.getOrDefault(A[i],0);
            int EdgeCountB = map.getOrDefault(B[i],0);
            map.put(A[i],EdgeCountA+1);
            map.put(B[i],EdgeCountB+1);
        }

        int result = 0;
        for(int i=0;i<A.length;i++){
            // for this pair of city, check if it is greater than the current result
            result = Math.max(map.get(A[i]) + map.get(B[i]) - 1 , result);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution6 s = new Solution6();
        int[] A = {1,2,4,5};
        int[] B = {2,3,5,6};

        System.out.println(s.solution(A,B,6));
    }
}
