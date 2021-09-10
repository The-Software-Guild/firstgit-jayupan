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

public class AllTheTrivia {
    public static void main(String[] args)
    {
        Scanner myScanner = new Scanner(System.in);
        
        String president, country, marsupial;
        
        System.out.println("Who is the current president of France?");
        president = myScanner.nextLine();
        
        System.out.println("Which country has a flag with a golden sun with eight rays?");
        country = myScanner.nextLine();
        
        System.out.println("Give an example of a marsupial.");
        marsupial = myScanner.nextLine();
        
        System.out.println("The current president of France is " + marsupial + "!");
        System.out.println("The " + president + " flag is a really cool design!");
        System.out.println("I really want a pet " + country + "!");
    }
}
