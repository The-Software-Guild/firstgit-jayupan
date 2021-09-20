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
import java.util.Random;
public class RockPaperScissors {
    public static void main(String [] args)
    {
        int userInput, comInput, rounds, userWins, comWins, ties;
        int result; //1 = player won, 2 = computer won, 3 = tie
        boolean rematch = false; //if true player keeps playing
        
        Scanner myScanner = new Scanner(System.in);
        Random rng = new Random(); //for random vals
        
        do {
            System.out.println("How many rounds of RPS would you like to play? (1-10)");
            userInput = Integer.parseInt(myScanner.nextLine()); //takes input and converts
            
            if(userInput < 1 || userInput > 10) //error handling for round errors
            {
                System.out.println("ERROR: " + userInput + " is incorrect input!");
                System.exit(0);
            }
            rounds = userInput;
            userWins = 0;
            comWins = 0;
            ties = 0;
            rematch = false;
            for(int i = 1; i < rounds+1; i++) // rounds + 1 so that we can print rounds val
            {
                System.out.println("Rock = 1, Paper = 2, Scissors = 3");
                userInput = Integer.parseInt(myScanner.nextLine());
                comInput = rng.nextInt(3)+1;
                if(userInput == 1 && comInput == 3) //rock > scissors
                {
                    result = 1;
                }
                else if(userInput == 2 && comInput == 1)//paper > rock
                {
                    result = 1;
                }
                else if(userInput == 3 && comInput == 2) //scissors > paper
                {
                    result = 1;
                }
                else if(comInput == 1 && userInput == 3)
                {
                    result = 2;
                }
                else if(comInput == 2 && userInput == 1)
                {
                    result = 2;
                }
                else if(comInput == 3 && userInput == 2)
                {
                    result = 2;
                }
                else 
                {
                    result = 3;
                }
                System.out.println("Player chose: " + userInput + ", Computer chose: " + comInput);
                switch (result) {   //choses winner time
                    case 1:
                        userWins++;
                        System.out.println("Player won round " + i + ".");
                        break;
                    case 2:
                        comWins++;
                        System.out.println("Computer won round " + i + ".");
                        break;
                    case 3:
                        ties++;
                        System.out.println("It is a tie for round " + i + "!");
                        break;
                    default:
                        break;
                }

            }
            
            //displays results
            System.out.println("Player wins: " + userWins + ".");
            System.out.println("Computer wins: " + comWins + ".");
            System.out.println("Ties: " + ties + ".");

            System.out.println("AND THE WINNER IS...");
            if(userWins > comWins) //declares winner if there is one
            {
                System.out.println("THE PLAYER!");
            }
            else if(comWins > userWins)
            {
                System.out.println("THE COMPUTER!");
            }
            else System.out.println("IT'S A TIE!");
            System.out.println("Would you like to play again?"); //prompts user for another 
            System.out.println("1 = yes, 2 = no");
            userInput = Integer.parseInt(myScanner.nextLine());
            if(userInput == 1)
            {
                rematch = true;   
            }
        }
        while (rematch); //loops if user opts for it
    }
}
