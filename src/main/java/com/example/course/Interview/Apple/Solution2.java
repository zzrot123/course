package com.example.course.Interview.Apple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution2 {
    public int solution(int[] T){
        // dfs
        // create the children of each city
        // city 0 : 2,6,9
        // city 1 : 9
        // city 2 : 0 , 3
        // city 3 : 2
        // city 4 : 6
        // city 5 : 8
        // city 6 : 4, 0
        // city 7 : 8
        // city 8 : 5,7 3
        // city 9 : 0, 1
        // have a boolean[] visited to keep track of visited city
        boolean[] visited = new boolean[T.length];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        buildGraph(T, map);
        for(int i=0;i<T.length;i++){
            visited[i] = false;
        }
        int res = dfs(map,visited,0,1);

        return res;
    }

    public int dfs(HashMap<Integer,List<Integer>> map, boolean[] visited, int currCity,int ticket){
        visited[currCity] = true;
        List<Integer> destCities = map.get(currCity);
        int max = 0;
        if(currCity%2==1){
            ticket--;
        }
        if(ticket<0) return max;
        for(int i = 0; i<destCities.size();i++){
            int destCity= destCities.get(i);
            if(!visited[destCity]){
                max = Math.max(max, dfs(map,visited,destCity,ticket));
            }
        }
        visited[currCity] = false;
        return 1 + max;
    }

    private void buildGraph(int[] T, HashMap<Integer,List<Integer>> map){
        for(int i=1;i<T.length;i++){
            // fromlist : the cities that the current city can go to
            List<Integer> fromList = map.getOrDefault(i,new ArrayList<>());
            List<Integer> toList = map.getOrDefault(T[i],new ArrayList<>());
            fromList.add(T[i]);
            toList.add(i);
            map.put(i,fromList);
            map.put(T[i],toList);
        }
    }

    public static void main(String[] args) {
        int[] T = {0,9,0,2,6,8,0,8,3,0};
        int[] T1 = {0,0,0,1,6,1,0,0};
        Solution2 s = new Solution2();
        System.out.println(s.solution(T));
        System.out.println(s.solution(T1));
    }
}
