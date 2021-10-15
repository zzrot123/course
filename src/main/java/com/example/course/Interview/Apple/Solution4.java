package com.example.course.Interview.Apple;

import java.util.*;

public class Solution4 {
    public int solution(int N, int[] A, int[] B){
        // use a map to store the vertices and its edge number
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<A.length;i++){
            int EdgeCountA = map.getOrDefault(A[i],0);
            int EdgeCountB = map.getOrDefault(B[i], 0);
            map.put(A[i],EdgeCountA+1);
            map.put(B[i],EdgeCountB+1);
        }
        // sort by freq from high to low
        List<Map.Entry<Integer,Integer>> entryList = new LinkedList<>(map.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        // assign the biggest value to highest freq count
        int result = 0;
        int value = N;
        for(int i = 0;i<entryList.size();i++){
            Map.Entry<Integer,Integer> entry = entryList.get(i);
            result += entry.getValue() * value;
            value--;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution4 s = new Solution4();
        int[] A = {2, 2, 1, 2};
        int[] B = {1,3,4,4};
        System.out.println(s.solution(5,A,B));
    }
}
