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

public class PassingTheTuringTest {
    public static void main(String [] args)
    {
        String name, color, fruit;
        int num;
        
        Scanner myScanner = new Scanner(System.in);
        
        System.out.println("Hello there!");
        System.out.println("What's your name? ");
        name = myScanner.nextLine();
        
        System.out.println("Hi, " + name + "! I'm Josef.");
        System.out.println("What's your favorite color? ");
        color = myScanner.nextLine();
        
        System.out.println("Really??? " + color + "? Mine's Sky Blue.");
        
        System.out.println("Sky Blue reminds me of blueberries. They're my favorite fruit...");
        System.out.println("What's your favorite fruit, " + name + "? ");
        fruit = myScanner.nextLine();
        
        System.out.println("You have good taste if you like " + fruit + ".");
        System.out.println("On the topic of favorites, what's your favorite number? ");
        num = Integer.parseInt(myScanner.nextLine());
        
        System.out.println("Oh, you like " + num + "... Anyways, my favorite number is 27.");
        System.out.println("Did you know that " + num + " * " + "27" + " = " + num*27 + "? Pretty cool right?");
        
        System.out.println("It was great talking to you " + name + "!");
        System.out.println("Smell you later!");
    }
}
