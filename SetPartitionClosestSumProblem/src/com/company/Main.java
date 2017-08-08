package com.company;

import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int[] arr = Stream.of(s.nextLine().trim().split(" ")).mapToInt(Integer::valueOf).toArray();
        System.out.println(SetPartitionClosestSum(arr));
    }
    private static int SetPartitionClosestSum(int[] arr){
        int sum = sumOf(arr);
        boolean[][] dpTable = new boolean[arr.length][sum/2+1];
        System.out.println("dpTable rows : "+dpTable.length);
        System.out.println("dpTable cols : "+dpTable[0].length);
        for(int i=0;i<dpTable.length;i++){
            dpTable[i][0] = true;
        }
        for(int i=1;i<dpTable[0].length;i++){
            if(arr[0]==i )
                dpTable[0][i] = true;
            else
                dpTable[0][i] = false;
        }
        for(int i=1;i<dpTable.length;i++){
            for(int j = 1;j<dpTable[0].length;j++){
                dpTable[i][j] = j-arr[i]>=0?dpTable[i-1][j]||dpTable[i-1][j-arr[i]]:dpTable[i-1][j];
            }
        }
        int minDiff = -99999;
        for(int j=dpTable[0].length-1;j>=0;j--){
            if(dpTable[dpTable.length-1][j]){
                minDiff = sum-2*j;
                return minDiff;
            }
        }
        print(dpTable);
        return minDiff;
    }
    private static int sumOf(int[] arr){
        int sum = 0;
        for(int each:arr){
            sum+=each;
        }
        return sum;
    }
    private static void print(boolean[][] dpTable){
        for(int i=0;i<dpTable.length;i++){
            for(int j=0;j<dpTable[0].length;j++){
                System.out.print(dpTable[i][j]+" ");
            }
            System.out.println("");
        }
    }
}
