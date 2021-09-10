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
import java.util.Scanner; //necessary for user input

public class HealthyHearts {
    
    public static void main(String[] args)
    {
        int age, heartRate; //init vars

        Scanner myScanner = new Scanner(System.in);

        System.out.println("What is your age?");
        age = Integer.parseInt(myScanner.nextLine());
        heartRate = 220-age;
        
        System.out.println("Your maximum heart rate should be " + heartRate + " beats per minute.");
        //uses Math.round to round double values to whole numbers 
        System.out.println("Your target HR Zone is " + Math.round(heartRate*0.5) + " - " + Math.round(heartRate*.85) + " beats per minute.");
        
    }
}
