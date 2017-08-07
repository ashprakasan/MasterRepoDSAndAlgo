package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int[] arr = Stream.of(s.nextLine().trim().split(" ")).mapToInt(Integer::valueOf).toArray();
        List<Integer> subset = new ArrayList<>();
        int sum = getSum(arr);
        boolean res = isSubsetDivisible(arr,arr.length-1,sum/2);
        if (res && sum%2==0)
            System.out.println("Can be partitioned equally.");
        else
            System.out.println("This set cannot be partitioned equally");
    }
    private static void print(List<Integer> l){
        for(Integer each:l){
            System.out.print(each+" ");
        }
        System.out.println("");
    }
    private static int getSum(int[] arr){
        int sum =0;
        for(int a:arr){
            sum+=a;
        }
        return sum;
    }
    private static boolean isSubsetDivisible(int[] arr,int n, int sum){
        if(n==-1&& sum ==0)
            return true;
        if(n==-1)
            return false;
        boolean res1 = isSubsetDivisible(arr,n-1,sum);
        boolean res2 = isSubsetDivisible(arr,n-1,sum-arr[n]);
        return (isSubsetDivisible(arr,n-1,sum)||isSubsetDivisible(arr,n-1,sum-arr[n]));
    }
//    private static List<Integer> add(List<Integer> arr, int num){
//        arr.add(num);
//        return arr;
//    }
}
