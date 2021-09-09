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
public class MenuOfChampions {
    public static void main(String [] args)
    {
        String item1, item2, item3;
        float price1, price2, price3;
        
        item1 = "Agedashi Tofu";
        price1 = 3.95f;
        item2 = "Cold Soba";
        price2 = 7.99f;
        item3 = "Kitsune Udon";
        price3 = 9.99f;
        
        System.out.println(".oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.\n" +
"\n" +
"            WELCOME TO RESTAURANT SUNSHINE CAFE!\n" +
"            Today's Menu Is...\n" +
"\n" +
".oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.");
        
       
    System.out.println("$ " + price1 + " *** " + item1);
    System.out.println("$ " + price2 + " *** " + item2);
    System.out.println("$ " + price3 + " *** " + item3);
    }
}
