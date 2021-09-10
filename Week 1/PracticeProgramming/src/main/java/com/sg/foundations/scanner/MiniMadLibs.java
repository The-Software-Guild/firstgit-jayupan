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

public class MiniMadLibs {
   public static void main (String[] args)
   {
       String noun1, adj1, noun2, adj2, plur1, plur2, plur3, presT, pasT;
       int num;
       
       Scanner myScanner = new Scanner(System.in);
       
       System.out.println("--- LET'S PLAY MAD LIBS ---");
       System.out.print("\n");
       System.out.println("I need a noun: ");
       noun1 = myScanner.nextLine();
       
       System.out.println("Now an adjective: ");
       adj1 = myScanner.nextLine();
       
       System.out.println("Another noun: ");
       noun2 = myScanner.nextLine();
       
       System.out.println("And a number: ");
       num = Integer.parseInt(myScanner.nextLine());
       
       System.out.println("Another adjective: ");
       adj2 = myScanner.nextLine();
       
       System.out.println("A plural noun: ");
       plur1 = myScanner.nextLine();
       
       System.out.println("Another one: ");
       plur2 = myScanner.nextLine();
       
       System.out.println("One more: ");
       plur3 = myScanner.nextLine();
       
       System.out.println("A verb (infinitive form): ");
       presT = myScanner.nextLine();
      
       System.out.println("Same verb (past participle): ");
       pasT = myScanner.nextLine();
       
       System.out.println("*** NOW LETS GET MAD (libs) ***");
       System.out.println(noun1 + ": the " + adj1 + " frontier. These are the voyages of the starship " + noun2 + "." +
            "Its " + num + "-year mission: to explore strange " + adj2 + " " + plur1 + ", to seek out " + adj2 + " " + plur2 + " and " + adj2 + " " + plur3 + ", " +
            "to boldly " + presT + " where no one has " + pasT + " before.");
   }
}
