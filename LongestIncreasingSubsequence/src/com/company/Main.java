package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] inp = s.nextLine().split(" ");
        int[] arr = new int[inp.length];
        int i = 0;
        for (String each : inp)
            arr[i++] = Integer.parseInt(each);
        System.out.println("Output using the more efficient algorithm (time complexity is nlogn) ");
        System.out.println("=====================================================================");
        longestIncreasingSubsequence(arr);
        System.out.println("Output using the DP algorithm (time complexity Nsquare)");
        System.out.println("========================================================");
        print(longestIncSubNSq(arr));
    }
    private static void print(List<Integer> arr){
        for(Integer each:arr){
            System.out.print(each+" ");
        }
        System.out.println("");
    }
    private static List<Integer> longestIncSubNSq(int[] arr){
        List<List<Integer>> L = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        List<Integer> l = new ArrayList<>();
        l.add(arr[0]);
        L.add(l);
        int maxLength,potListPos;
        for(int i=1;i<arr.length;i++){
            maxLength =0;
            potListPos =-1;
            for(int j=0;j<i;j++){
                if(lastElement(L.get(j))<arr[i]&&L.get(j).size()>maxLength){
                    maxLength = L.get(j).size();
                    potListPos = j;
                }
            }
            if(potListPos!=-1){
                L.add(L.get(potListPos));
                L.get(i).add(arr[i]);
            }
            else{
                l = new ArrayList<>();
                l.add(arr[i]);
                L.add(l);
            }
        }
        maxLength = 0;
        for(List<Integer> each:L){
            if(each.size()>maxLength){
                res = each;
                maxLength = each.size();
            }
        }
        return res;
    }
    private static int lastElement(List<Integer> a){
        return a.get(a.size()-1);
    }
    private static void longestIncreasingSubsequence(int[] arr){
        int[] temp = new int[arr.length];
        int[] res = new int[arr.length];
        int maxLen = 1,pos;
        temp[0] = 0;
        res[0] = -1;
        for(int i=1;i<arr.length;i++){
            if(arr[i]>arr[temp[maxLen-1]]){
                maxLen++;
                temp[maxLen-1] = i;
                res[i] = temp[maxLen-2];
            }
            else{
                pos = binSearchCiel(arr,temp,arr[i],maxLen-1);
                temp[pos] = i;
                if(pos!=0)
                    res[i] = temp[pos-1];
                else
                    res[i] = -1;
            }
        }
        StringBuilder sb = new StringBuilder();
        System.out.println("Length of max increasing subsequence is "+maxLen);
        pos = temp[maxLen-1];
        while(pos!=-1){
            sb.append(arr[pos]);
            sb.append(" ");
            pos = res[pos];
        }
        System.out.println(sb.toString());
    }
    private static int binSearchCiel(int[] arr, int[] temp, int num, int high){
        int low = 0;
        int mid;
        int res =-1;
        while(low<=high){
            mid = (low+high)/2;
            if(arr[temp[mid]]>=num){
                res = mid;
                high = mid-1;
            }
            else{
                low = mid+1;
            }
        }
        return res;
    }
}
