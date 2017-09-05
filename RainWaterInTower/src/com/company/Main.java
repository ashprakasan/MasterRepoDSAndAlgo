package com.company;

import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
	Scanner s = new Scanner(System.in);
	int[] arr;
	arr = Stream.of(s.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(waterBetween(arr));
    }
    private static int waterBetween(int[] arr){
        int[] maxToLeft = new int[arr.length];
        int maxSoFar =arr[0];
        maxToLeft[0] = -1;
        for(int i=1;i<arr.length;i++){
            if(arr[i]<maxSoFar){
                maxToLeft[i] = maxSoFar;
            }else{
                maxToLeft[i] = -1;
                maxSoFar = arr[i];
            }
        }
        int waterHeld = 0;
        maxSoFar = arr[arr.length-1];
        for(int i= arr.length-1;i>=0;i--){
            if(arr[i]<maxSoFar){
                //System.out.println("Adding to water held "+(min(maxToLeft[i],maxSoFar)-arr[i]));
                if(maxToLeft[i]!=-1)
                    waterHeld+=(min(maxToLeft[i],maxSoFar)-arr[i]);
            }else{
                maxSoFar = arr[i];
            }
        }
        return waterHeld;
    }
    private static int min(int a, int b){
        return a<b?a:b;
    }
}
