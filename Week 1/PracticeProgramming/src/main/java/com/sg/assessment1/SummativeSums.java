/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.assessment1;

/**
 *
 * @author Josef
 */
public class SummativeSums {
    public static void main(String [] args)
    {
        int arr1[] = { 1, 90, -33, -55, 67, -16, 28, -55, 15};
        int arr2[] = { 999, -60, -77, 14, 160, 301 };
        int arr3[] = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 
        140, 150, 160, 170, 180, 190, 200, -99 } ;
        
        System.out.println("#1 Array Sum: " + sumArray(arr1));
        System.out.println("#2 Array Sum: " + sumArray(arr2));
        System.out.println("#3 Array Sum: " + sumArray(arr3));
    }
    
    static int sumArray(int[] arr)
    {
        int result = 0; //return val
        for(int i = 0; i < arr.length; i++) result += arr[i]; //loops through array and adds each number to result
        return result;
    }
}
