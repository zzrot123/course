package com.example.course.Interview.Apple;

public class Solution5 {

    // use % to find the offset in the string array
    public String solution(String S, int K){
        String[] str = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        int day = -1;
        for(int i=0;i<str.length;i++){
            if(S.equals(str[i])){
                day = i;
            }
        }
        return str[(day + K )%7];
    }

    public static void main(String[] args) {
        Solution5 s = new Solution5();
        System.out.println(s.solution("Wed",2));
        System.out.println(s.solution("Sat",23));
    }

}
