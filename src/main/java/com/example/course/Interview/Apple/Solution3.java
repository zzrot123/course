package com.example.course.Interview.Apple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution3 {
    // [51,71,17,42]
    // build a map which contains the digit sum as key, and the value is list of integers that add up to this sum
    public int solution(int[] A){
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int num : A){
            int digitSum = getDigitSum(num);
            List<Integer> list = map.getOrDefault(digitSum,new ArrayList<>());
            list.add(num);
            map.put(digitSum, list);
        }
        int result = -1;
        for(int key : map.keySet()){
            List<Integer> list = map.get(key);
            if(list.size()==1) continue;
            result = Math.max(result, getMaxSum(list));
        }

        return result;
    }
    private int getMaxSum(List<Integer> list){
        int maxNum  = list.get(0);
        int repeatCount = 1;
        for(int i=1;i<list.size();i++){
            if(list.get(i) >= maxNum){
                if(maxNum==list.get(i)){
                    repeatCount++;
                }else {
                    repeatCount = 1;
                    maxNum = list.get(i);
                }
            }
        }
        if(repeatCount>1) return maxNum * 2;
        int secondMax = list.get(0);
        if(secondMax == maxNum) secondMax = list.get(1);
        for(int i=0;i<list.size();i++){
            if(list.get(i)>secondMax){
                if(list.get(i)==maxNum){
                    continue;
                }else{
                    secondMax = list.get(i);
                }
            }
        }
        return maxNum + secondMax;
    }


    private int getDigitSum(int num){
        int digitSum = 0;
        while(num>0){
            digitSum += num % 10;
            num = num / 10;
        }
        return digitSum;
    }

    public static void main(String[] args) {
        Solution3 s = new Solution3();
        int[] A = {51,71,17,42};
        int[] A2 = {42,33,60};
        int[] A3 = {51,32,43};
        System.out.println(s.solution(A));
        System.out.println(s.solution(A2));
        System.out.println(s.solution(A3));
    }

}
