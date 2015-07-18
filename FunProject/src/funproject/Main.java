/*
 * Going to create a program that acts like a vending machine. User will be able
 * to either insert money, or choose what he wants and then the machine will
 * prompt him to enter the appriate amount. If user adds too much money the
 * machine will dispense the product then give correct change. If the user added
 * money first, but didn't add enough the machine will prompt him to add more
 * or reselect.

testing this change
new one
test this change too
 */

package funproject;
import java.io.*;
//import java.lang.*;
/**
 *
 * @author Derek
 */
public class Main {


    private static class Drinks {

        int item_count = 8;
        String[] bev = {
            "Coke",
            "Diet Coke",
            "Sprite",
            "Rootbeer",
            "Orange Juice",
            "Apple Juice",
            "Insert Money",
            "Back to Main Menu"};
        double[] price = {
            .50,
            .50,
            .50,
            .50,
            1.0,
            1.0,
            0,
            0};
      // int length = bev[0].length();
    }

    public static void main(String[] args) {
        int creds = 0, error = 0; //machine starts everything at null or zero
        String input = null;

        mainMenu( creds , input, error );

    }
    public static void mainMenu(double user_credits, String user_input,
            int error_count ){

        int selection = 99;

        if( error_count >= 10) {
            System.out.println("Too many errors have occured. Exiting program.");
            refund( user_credits );
            System.exit(1);
        }
        else {
          //user menu starts here
            System.out.println( '\n' + "Welcome to the vending machine.");
            System.out.println("Please select from the following options:");
            System.out.println("1. Insert Money");
            System.out.println("2. See Menu");
            System.out.println("3. Refund Money");
            System.out.println("4. Exit");
            System.out.print("Current Funds: ");
            System.out.print(user_credits);
            System.out.println();
            System.out.print("Enter your choice:");

            user_input = userInput();
            selection = choice( user_credits, user_input);
            
            if( selection == 1 ) {
                user_credits = money(user_credits);
                mainMenu( user_credits, null, error_count);
            }
            else if( selection == 2 )
                menu(user_credits, error_count);
            else if( selection == 3 ) {
                user_credits = refund(user_credits);
                mainMenu( user_credits, null, error_count);
            }
            else if( selection == 4 ) {
                refund(user_credits);
                System.out.println("Goodbye");
                System.exit(1);
            }
            else{
                    errorControl( 1, user_credits, error_count );
            }

        System.out.print(user_input);
        System.out.println();
        }
    }

    public static String userInput(){
        String user_input = null;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
                user_input = br.readLine();
        } catch (IOException e) {
            System.out.println("Error!");
            System.exit(1);
        }
        return(user_input);
    }
    public static int choice( double user_credits, String user_choice) {
            int selection = 99;
            
     //       selection = (Integer.valueOf(user_choice) ).intValue();
          //  System.out.print("are we here");
      //      return( selection );
        try {
                selection = (Integer.valueOf(user_choice) ).intValue();
        }
         catch ( NumberFormatException e ){
            System.out.println("Error converting user request to integer format."
                    +" Program is closing.");
            System.exit(1);
        }
            return( selection );


    }
    public static void errorControl(int errorCode, double user_credits,
            int error_count) {

        error_count++;

        if( errorCode == 1 ){
            System.out.println( "An error has occured with your selection. " +
                    "Sending you back to main menu. Please try again.");
            mainMenu(user_credits, null, error_count);
        }
        if( errorCode == 2 ){
            System.out.println( "An error has occured with your menu" +
                    " selection. Sending you back to main menu. Please try" +
                    " again.");
            mainMenu(user_credits, null, error_count);
        }
        if( errorCode == 3 ){
            System.out.println( "An error has occured with check" +
                    " funds. Sending you back to main menu. " +
                    " Please try again.");
            mainMenu(user_credits, null, error_count);
        }
        else {
            System.out.println( "An error has occured. " +
                    "Sending you back to main menu. Please restart, Sorry." );
            mainMenu(user_credits, null, error_count);
        }

    }

    public static double money(double user_credits) {

        System.out.println("Please add money: ");
        String money = userInput();
        double added = 0;

        try {
                added = (Double.valueOf(money) ).doubleValue();
        }
         catch ( NumberFormatException e ){
            System.out.println("Error converting user request to double format."
                    +" Program is closing.");
            System.exit(1);
        }
        if( added <= 0 ){
            System.out.println("You didn't add any money");
            return user_credits;
        }
        else
            added = added+user_credits;
       //     System.out.println("Current Funds: ");
        //    System.out.print(selection);
            return( added );

    }
    public static void menu(double user_credits, int error_count){
        String user_input = null;
        int selection = 99;
        double price_of_pop = .5;
        double price_of_juice = 1;
        
        Main.Drinks menu_items = new Drinks();

//        System.out.println( '\n' + "Menu Items." + '\n'
//                + menu_items.bev[0]
//                + '\n' + menu_items.bev[1] + menu_items.price
//                + '\n' + menu_items.bev[2]
//                + '\n' + menu_items.bev[3]
//                + '\n' + menu_items.bev[4]
//                + '\n' + menu_items.bev[5]
//                + '\n' + menu_items.bev[6]
//                + '\n' + menu_items.bev[7] +'\n'
//                + "Please make your Selection: ");
//        System.out.println( '\n' + "Menu Items." + '\n' );
        //creating a print loop

        for (int slength = 1;slength <= menu_items.item_count; slength++) {
            System.out.print( slength + ". " + menu_items.bev[slength - 1] );
            if( slength < (menu_items.item_count - 1) )
                   System.out.format( " $ %1.2f%n" ,
                           menu_items.price[slength - 1]);
            else
                System.out.println();
        }
        System.out.println( "Please make your Selection: ");
        user_input = userInput();
        selection = choice( user_credits, user_input );
        if( selection > 8 || selection < 1 )
            errorControl( 2 , user_credits, error_count);
        else if( selection == 7 ) {
            user_credits = money(user_credits);
            menu(user_credits, error_count);
        }
        else if( selection == 8 )
            mainMenu( user_credits, user_input, error_count);
        else if( selection  <= menu_items.item_count ){
                user_credits = checkFunds( user_credits, 
                        menu_items.price[selection], error_count);
                    System.out.println("Here is your "
                            + menu_items.bev[selection] );
            }
             //   else
               //     errorControl( 2, user_credits, error_count );
              //  user_credits = user_credits - price_of_pop;
            
//            else if (selection == 5 || selection == 6){
//                user_credits = checkFunds( user_credits, price_of_juice,
//                        error_count);
//                if( selection == 5)
//                        System.out.println("Here is your Apple Juice.");
//                else if( selection == 6)
//                        System.out.println("Here is your Orange Juice.");
//                else
//                        errorControl( 2, user_credits, error_count );
                
//            }
            else
                errorControl( 2, user_credits, error_count );
       //mainMenu( user_credits, user_input, error_count);
    }
    public static double refund(double user_credits){

        if( user_credits <= 0 )
            System.out.println("Nothing to refund");
        else {
            System.out.println("Thank you for your business. " + '\n' +
                 "Refunding " + user_credits );
            user_credits = 0; // Zero's out user credits
        }
        return user_credits;

    }
    public static double checkFunds( double user_credits, double price,
            int error_count) {
            double left_over = (user_credits - price);

        if( left_over < 0 ) {
            System.out.println("Insufficient funds!" + '\n' + "You are short " +
                    (user_credits - price) + '\n' +
                    "1. Add more money" + '\n' + "2. Exit to Main Menu");
            String user_input = userInput();
            int selection = choice( user_credits, user_input);
            if(selection == 1) {
                user_credits = money(user_credits);
                user_credits = checkFunds( user_credits, price, error_count);
            }
            else if(selection == 2)
                mainMenu( user_credits, user_input, error_count);
            else
                errorControl( 3, user_credits, error_count );

        }
        else if(left_over > 0)
              return(left_over);
        return(0);

        
    }



}
