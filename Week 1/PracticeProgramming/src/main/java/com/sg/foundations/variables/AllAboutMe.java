/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.variables;

/**
 *
 * @author Josef
 */
public class AllAboutMe {
    public static void main(String[] args)
    {
        String name, food, home;
        int pets;
        boolean whistle;
        
        name = "Josef Ayupan";
        food = "grilled mackerel";
        home = "studio apartment";
        
        pets = 1;
        whistle = true;
        
        System.out.println("My name is " + name + ".");
        System.out.println("My favorite food is " + food + ".");
        System.out.println("I have " + pets + " pets.");
        System.out.println("I live in a " + home + ", and I love it here.");
        System.out.println("It is " + whistle + " I know how to whistle.");
    }
}
