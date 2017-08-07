package com.company;

import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.nextLine();
        List<int[]> inpArray = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] row;
            row = Stream.of(s.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
            inpArray.add(row);
        }
        List<int[]> arr = getAllRotations(inpArray);
        arr.sort((o1, o2) -> (o1[0] * o1[1] - o2[0] * o2[1]));
        print(arr);
        int[] maxH = new int[arr.size()];
        maxH = Arrays.stream(maxH).map(i->-1).toArray();
        maxH[0] = arr.get(0)[2];
        int[] curr,ind;
        int height;
        for(int i=1;i<arr.size();i++){
            ind = arr.get(i);
            height = ind[2];
            for(int j=0;j<i;j++){
                curr = arr.get(j);
                if(curr[0]<ind[0]&&curr[1]<ind[1])
                    height = max(height,ind[2]+maxH[j]);
            }
            maxH[i] = height;
        }
        for(int each:maxH){
            System.out.println(each);
        }
    }

    private static int max(int a, int b){
        return a>b?a:b;
    }
    private static List<int[]> getAllRotations(List<int[]> inpArray) {
        List<int[]> res = new ArrayList<>();
        int[] app;
        for (int[] arr : inpArray) {
            app = new int[3];
            Arrays.sort(arr);
            app[0] = arr[2];
            app[1] = arr[1];
            app[2] = arr[0];
            res.add(app);
            app = new int[3];
            app[0] = arr[2];
            app[1] = arr[0];
            app[2] = arr[1];
            res.add(app);
            app = new int[3];
            app[0] = arr[1];
            app[1] = arr[0];
            app[2] = arr[2];
            res.add(app);
        }
        return res;
    }

    private static void print(List<int[]> arr) {
        for (int[] b : arr) {
            System.out.println(b[0] + " " + b[1] + " " + b[2]);
        }
    }
}
