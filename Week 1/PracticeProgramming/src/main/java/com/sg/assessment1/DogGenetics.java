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
import java.util.Scanner;
import java.util.Random; 

public class DogGenetics {
    //list of dogs
    static String[] dogs = { "German Weimraner" ,
        "Chinese Chow Chow",  
        "Pomeranian",
        "Akita Inu",
        "German Mastiff",
        "Newfoundland",
        "Pembroke Welsh Corgi",
        "French Bulldog",
        "Rottweiler",
        "Bull Terrier" } ;
    
    public static void main(String[] args)
    {
        //initalize variables
        String[] myDogs = new String[5];
        String input;
        int dna = 100;
        boolean isDupe = false;
                
        Scanner myScanner = new Scanner(System.in);
        Random rng = new Random(); //for random numbers
                
        System.out.println("What is your dog's name?");
        input = myScanner.nextLine();
       
        System.out.println("Well then, I have this highly reliable report on " + input +
                "'s prestigious background right here.");
        System.out.println(input + " is:");
              
        for(int i = 0; i < 5; i++) //5 is the specifications given by the assignment
        {
            String result = dogs[rng.nextInt(dogs.length)]; //picks a random String from the dogs array
            for (String myDog : myDogs) {
                if (result.equals(myDog)) {
                    isDupe = true;
                }
            }
            
            if(!isDupe) //if there's no duplicate
            {
                myDogs[i] = result;
                int val = rng.nextInt(dna-4+i)+1; //rng.nextInt(dna-4+i)+1 is so that there's never a 0 value
                if(i == 4) //last iteration
                {
                    val = dna; //val is now the remaining dna
                }
                else dna -= val; //dna subtracts itself from the value being displayed
                System.out.println(val + "% " + result);
            }
            else  
            {
                i--;//reiterates loop without incrementing to maintain array size
                isDupe = false;
            }
        }
    }
}
