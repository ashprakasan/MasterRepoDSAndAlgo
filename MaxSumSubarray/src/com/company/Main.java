package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] line = s.nextLine().split(" ");
        int[] arr = new int[line.length];
        int i = 0;
        for (String each : line)
            arr[i++] = Integer.parseInt(each);
        print(maxSumSubarray(arr));
    }

    private static void print(List<Integer> arr) {
        for (Integer each : arr) {
            System.out.print(each + " ");
        }
        System.out.println("");
    }

    private static List<Integer> maxSumSubarray(int[] arr) {
        int maxSoFar = arr[0];
        int start = 0;
        int end = 0;
        int pstart = 0;
        int maxEndingHere = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxEndingHere + arr[i]) {
                maxEndingHere = arr[i];
                pstart = i;
                end = i;
            }
            else
                maxEndingHere += arr[i];
            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                start = pstart;
                end = i;
            }
        }
        List<Integer> res = new ArrayList<Integer>();
        for(int i=start;i<=end;i++){
            res.add(arr[i]);
        }
        return res;
    }
}
