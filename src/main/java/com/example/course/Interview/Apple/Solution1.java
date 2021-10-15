package com.example.course.Interview.Apple;

public class Solution1 {
    // case 1: contains 0 => return 0
    // case 2: even number of negative number => return 1
    // case 3: odd number of negative number => return -1
    public static int solution(int[] A){
        int negativeCount = 0;
        for(int num : A){
            if(num==0){
                return 0;
            }else if(num < 0){
                negativeCount++;
            }
        }
        if(negativeCount%2==0){
            return 1;
        }else{
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] A1 = {1,-2,-3,5};
        int[] A2 = {1,2,3,-5};
        int[] A3 = {1,2,0,-5};
        System.out.println(Solution1.solution(A1));
        System.out.println(Solution1.solution(A2));
        System.out.println(Solution1.solution(A3));
    }
}
