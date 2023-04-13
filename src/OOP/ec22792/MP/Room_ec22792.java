package OOP.ec22792.MP;

import javax.swing.*;
import java.util.Random;

class Room_ec22792 extends Room{
    public Visitor v;
    //visit a place
    public Direction visit(Visitor v, Direction d){
        this.v= v;
        int gold=0;
        boolean windowOpen= false;
        boolean ghosts= true;
        
        welcomeMsg();
        settings();
        gold = giveGold(gold, 5);
        
        if (!windowOpen) {
            char option= options();
            if (option=='a'){
            d=move(d);
            }
            else if (option=='b') {
                String name=itemChoice();
                v.giveItem(new Item(name));
            }
            else if (option== 'c') {
                gold= gold + goldMystery(gold);
            }
            else if (option== 'd') {
                surprise();
            }
            else if (option=='e'){
                gold= fight(gold);
                ghosts= false;
            }
            else {
                v.tell("Invalid option: you are banished!");
            }
        }
        
        else{
            ghosts=false;
            v.tell("The ghosts left through the window");
            v.tell("Not much to do around here");
            windowOpen= true;
        }

        v.giveGold(gold);
        v.tell("Goodbye. I shall see you soon. On the other side...");

        return d;
    }
    
    //Fight
    int fight(int gold){
        v.tell("Oh the Winchester ghosts are fighting again!");
        v.tell("Do me a favour and stop them");
        
        int health= 20;
        v.tell("The ghost's health is " + health);
        String name= ghost();
        v.tell("You are fighting " + name);
        char[] options= {'a','b','c'};
        char choice= v.getChoice("a) left hook b) kick c) do nothing?", options);
        if (choice =='c') {
            v.tell("No confrontation: fair enough");
        }
        
        while ((health>0) || (choice!='c')){
            if (choice=='a') {
                v.tell("*punch noises*");
                health= changeHealth(health);
                gold= gold+2;
            }
            else if (choice=='b'){
                v.tell("*kicking noises*");
                health=changeHealth(health);
                gold=gold+1;
            }
            else {
                break;
            }
        }
        return gold;
    }
    
    //Change health
    int changeHealth(int health) {
        Random ran= new Random();
        int damage= ran.nextInt(21);
        health= health - damage;
        if (health<0) {
            health=0;
        }
        return health;
    }
    
    //Pick random ghost
    String ghost() {
        String name="";
        Random ran= new Random();
        int ghost= ran.nextInt(4);
        
        if (ghost==0) {
            name= "Sarah Winchester";
        }
        else if (ghost==1) {
            name = "Annie Pardee Winchester";
        }
        else if (ghost==2) {
            name= "William Wirt Winchester";
        }
        else {
            name= "Mother-in-law Winchester";
        }
        
        return name; 
    }
   
    //Surprise
    void surprise() {
        v.tell("Ooh we are taking quite the risk I see");
        v.tell("You are going to have to face a terribly hard challenge");
        v.tell("Almost comparable to Heracles' 12 labours");
        v.tell("Your task is to find a friend and...");
        v.tell("Choose who is moving using WASD and who with the arrows");
        v.tell("And take a break. Here is the link");
        v.tell("https://www.coolmathgames.com/0-fireboy-and-water-girl-in-the-forest-temple");
        return;
    }
    
    //Gold mystery: take or give some?
    int goldMystery(int gold){
        int given=0;

        v.tell("Ooh adventerous I see");
        v.tell("Will you get gold, or lose it?");
        v.tell("If you can answer this question, you'll earn some gold.");
        v.tell("What was the prisoner number for Jean Valjean? (No hints here)");
        String ans= inputString();
        ans= ans.toLowerCase();
        
        if (ans.equals("24601")){
            v. tell("I see you have taste! Here is 10 gold");
            given=10;
        }
        else {
            v.tell("The answer is 24601");
            v.tell("Shame on you for not knowing *tsk*");
            v.tell("Leave this room and go watch/read/listen Les Miserables");
            v.tell("You lost 3 golds");
            given=-3;
        }
        
        return given;
    }
    
    
    //Give an item to the user
    String itemChoice(){
        v.tell("You have two options. Option a or b");
        v.tell("I am not going to tell you what they are. But I will give you hints");
        v.tell("Option A can be helpful (or not) for you or another thing here in this room.");
        v.tell("You probably need Option B");
        
        char[] options= {'a', 'b'};
        char choice= v.getChoice("So which one will it be a or b?", options);
        String name="";
        
        if (choice=='a'){
            v.tell("You have just received *drum roll please* a cup of water!");
            v.tell("You can drink it or water those flowers in the corners");
            v.tell("I can assure you it is not poisoned... Or is it?");
            name="water";
        }
        else if (choice=='b'){
            v.tell("Here is a bar of soap: after your journey you need it!");
            v.tell("The first soap was made from heroes' ashes like the first monkey shot into space");
            name="soap";
        }
        else {
            v.tell("Incorrect choice");
            itemChoice();
        }
        return name;
    }
    
    //Give user options for actions
    char options() {
        char[] options={'a', 'b', 'c', 'd', 'e'};
        v.tell("Choose your options");
        String msg="a) move b) get an item c) gold mystery d) surprise e) fight";
        char choice= v.getChoice(msg, options);
        return choice;
    }
    
    //Move user in the opposite direction they chose
    Direction move(Direction d){
        char [] options = {'n', 's', 'w', 'e'};
        Direction dir= d;
        
        String msg="Which directions do you want to go in: North, South, West, East?";
        char choice=v.getChoice(msg, options);
        
        if (choice=='n') {
            dir= d.TO_SOUTH;
        }
        else if (choice=='s'){
            dir= d.TO_NORTH;
        }
        else if (choice=='w'){
            dir= d.TO_EAST;
        }
        else if (choice=='e'){
            dir= d.TO_WEST;
        }
        else {
            v.tell("Incorrect option");
            move(d);
        }
   
        return dir;
        
    }

    //Tell the user something
    void tell(String s) {
        JOptionPane.showMessageDialog(null, s);
        return;
    }

    //Get the user's choice
    public char getChoice(String descriptionOfChoices, char[] arrayOfPossibleChoices) {
        char choice;
        boolean valid = false;
        JOptionPane.showMessageDialog(null, descriptionOfChoices);
        String ans = JOptionPane.showInputDialog(null, "Enter choice");
        choice = ans.charAt(0);

        while (!valid) {
            for (char x : arrayOfPossibleChoices) {
                if (choice == x) {
                    valid = true;
                    break; // exit the loop once a valid choice is found
                }
            }
            if (!valid) {
                ans = JOptionPane.showInputDialog(null, "Invalid choice " + descriptionOfChoices);
                choice = ans.charAt(0);
            }
        }
        return choice;
    }


    //Describe settings
    void settings(){
        tell("You are in the relax room. Rest a bit");
        tell("There is a flower pot in the corner. They look a bit dead, don't they?");
        tell("Sorry about the awfully orange sheets and old furniture.");
        tell("Haunted houses have a standard to follow, you know?");
        tell("Sarah Winchester made sure of that...");
        tell("We got some detailed ceilings and chandeliers though");
        tell("I wonder if the Phantom would like them... Anyway");
        return;
    }
    //Welcome message
    void welcomeMsg () {
        
        String hello= "Welcome to room ec22792!";
        String welcome= "The room where we just want to rest after a good day's work";
        String goldGive= "Here are 5 golds as a reward for your hard work";
        tell(hello);
        tell(welcome);
        tell(goldGive);
        return;
    }
    
    //Give gold
    int giveGold( int newGold, int total){
        total= total+newGold;
        return total;
    }
    
    //inputString
    String inputString() {
        String ans= JOptionPane.showInputDialog(null,"");
        return ans;
    }


}
        
