package OOP.ec22792.A8;

import java.util.Scanner;
import java.io.PrintStream;
import java.io.InputStream;
import java.util.Random;

class House_ec22792 extends House{
    private Room r1;
    private Room r2;
    private Room r3;
    private Room r4;
    private Room current;
    private static PrintStream ps= System.out;
    private static InputStream is= System.in ;

    //main to test
   /* public static void main(String[] args){
        OOP.ec22792.A8.House h= new OOP.ec22792.A8.House_ec22792();
        IOVisitor v= new IOVisitor(ps, is);
        OOP.ec22792.A8.Direction d= OOP.ec22792.A8.Direction.TO_WEST;
        d= h.visit(v,d);
    } */
    
    //Constructor
    House_ec22792() {
        //My room
        r1= new Room_ec22792();
        
        //Rooms chosen at random from A5 contributions
        r2= new Room_ec22433();
        r3= new Room_ec22562();
        r4= new Room_ec22860();
    }
    
    //visit method
    public Direction visit(Visitor v, Direction d) {
        int gold=0;
        char choice;
        char[] options= {'a', 'b', 'c', 'd'};

        Random r= new Random();
        //Generate total times a user is allowed to visit the house
        int allowed=r.nextInt(5);
        System.out.println(allowed);

        welcome(v);
        gold= giveGold(gold, 5, v);
        tell("Start your adventure in one of the rooms: good luck!");

        while (allowed>=0){

            //Have user move to the first room
            choice= getChoice("Go to: a) ec22433 b) ec22792 c) ec22860 d) ec22562 ", options);

            if (choice=='a'){
                d= r2.visit(v, d);
                current=r2;
            }
            else if (choice=='b'){
                d= r1.visit(v, d);
                current=r1;
            }
            else if (choice=='c'){
                d= r4.visit(v, d);
                current=r4;
            }
            else {
                d=r3.visit(v, d);
                current=r3;
            }

            //If direction returned is SOUTH, the user leaves
            if ((d==d.TO_SOUTH) || (d==d.FROM_SOUTH)) {
                tell("Your journey ends here");
                tell("Take some gold for the journey");
                gold= giveGold(10, gold, v);
                tell("Now OUT OF HERE!");
            }
            else {
                tell("Where do you want go?");
                choice= getChoice("a) North b) South c) East d) West?", options);
                if (choice=='a') {
                    if (current==r1) {
                        current= r2;
                    }
                    else if ((current==r2) || (current==r3)){
                        tell("You cannot go this way");
                        tell("Putting you somewhere else...");
                    }
                    else {
                        current= r3;
                    }
                }
                else if (choice=='b') {
                    if ((current==r1) || (current==r4)) {
                        tell("You cannot go this way");
                        tell("Putting you somewhere else...");
                        current=r3;
                    }
                    else if (current==r2) {
                        current=r1;
                    }
                    else if (current==r3) {
                        current=r4;
                    }
                }
                else if (choice=='c') {
                    if ((current==r1) || (current==r2)){
                        tell("You cannot go this way");
                        tell("Putting you somewhere else...");
                        current=r4;
                    }
                    else if (current==r3) {
                        current= r2;
                    }
                    else if (current==r4) {
                        current= r1;
                    }
                }
                else {
                    if ((current==r4) || (current==r3)) {
                        tell("You cannot go this way"); 
                        tell("Putting you somewhere else...");
                        current=r1;
                    }
                    else if (current==r1) {
                        current=r4;
                    }
                    else if (current==r2) {
                        current=r3;
                    }
                }
            }
            allowed--;
            System.out.println(allowed);
            d= current.visit(v,d);
        }
        
        return d;
    }
    

    //Welcome message
    void welcome(Visitor v) {
        v.tell("Welcome to the OOP.ec22792.A8.House of ec22792!");
        v.tell("Here is what it looks like:");
        v.tell(" - - - - - - - - - - - - - -");
        v.tell("|            |              |");
        v.tell("|     ec     |      ec      |");
        v.tell("|    22562   |    22433     |");
        v.tell("|            |              |");
        v.tell(" - - - - - - - - - - - - - - - - - - ");
        v.tell("|                                   |");
        v.tell(" - - - - - - - - - - - - - -        |");
        v.tell("|            |              |       | - - - - - ");
        v.tell("|      ec    |     ec       |       |    You    |");
        v.tell("|    22860   |    22792     |       |    are    |");
        v.tell("|            |              |       |    here   |");
        v.tell("|            |              |       | - - - - -");
        v.tell(" - - - - - - - - - - - - - - - - - - - - - - - - ");
        return;
    }
    
     //Give gold
    int giveGold( int newGold, int total, Visitor v){
        v.tell("You have received " + newGold + " gold");
        total= total+newGold;
        v.tell("You have " + total + " gold");
        return total;
    }
    
    //Tell the user something
    void tell(String s) {
        System.out.println(s);
        return;
    }
    
    //Get the user's choice
    char getChoice(String choice, char [] options){

        System.out.println(choice + " " + options);
        String uChoice = inputString();
        uChoice= uChoice.toLowerCase();
        char uChar= uChoice.charAt(0);
        
        boolean valid= check(uChar, options);
        
        while (!valid){
            tell("Invalid choice! Try again");
            uChoice=inputString();
            uChoice= uChoice.toLowerCase();
            uChar= uChoice.charAt(0);
            valid=check(uChar, options);
        }
        
        return uChar;
    }
    
    //Check if a choice is valid
    boolean check(char choice, char[] options){
        boolean valid= false;
        for (char x : options) {
            if (x==choice) {
                valid=true;
            }
        }
        return valid;
    }
    
    //inputString
    String inputString() {
        Scanner sc= new Scanner(System.in);
        String ans= sc.nextLine();
        return ans;
    }
    
    
}
