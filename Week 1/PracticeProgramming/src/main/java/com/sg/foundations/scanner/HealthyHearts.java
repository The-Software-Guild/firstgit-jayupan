/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.scanner;

/**
 *
 * @author Josef
 */
import java.util.Scanner;

public class HealthyHearts {
    
    public static void main(String[] args)
    {
        int age, heartRate;

        Scanner myScanner = new Scanner(System.in);

        System.out.println("What is your age?");
        age = Integer.parseInt(myScanner.nextLine());
        heartRate = 220-age;
        
        System.out.println("Your maximum heart rate should be " + heartRate + " beats per minute.");
        System.out.println("Your target HR Zone is " + heartRate*0.5 + " - " + heartRate*.85 + " beats per minute.");
        
    }
}
