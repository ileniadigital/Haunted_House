package OOP.ec22792.MP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.InputStream;
import java.util.Random;

class House_ec22792 extends House implements ActionListener {
    public JFrame f= new JFrame();
    public JPanel p1;
    public JPanel p2;
    public JPanel p3;
    public static JLabel gold;
    public static int goldTot= 0;
    public static JList<Item> items;
    public static JLabel itemsLabel;
    public static HashMap<Item,String> itemList= new HashMap<Item,String>();
    public static String itemsString="";
    public JLabel welcome;
    public JTextArea map;
    public JButton a;
    public JButton b;
    public JButton c;
    public JButton d;
    public final Visitor v= new GUIVisitor_ec22792();
    public Direction dir= new Direction();
    public HashMap<Room, String> floor1= new HashMap<Room,String>();
    public HashMap<Room, String> floor2= new HashMap<Room,String>();
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
    House_ec22792(JFrame f) {
        //Create GUI
        /*Rooms on the first floor*/
        //My room
        floor1.put(new Room_ec22792(), "ec22792") ;
        //Rooms chosen at random from A5 contributions
        floor1.put(new Room_ec22433(), "ec2433");
        floor1.put(new Room_ec22562(), "ec22562");
        floor1.put(new Room_ec22860(), "ec22860");

        /*Rooms on second floor*/
        //TO PICK

        //Setting the frame size to a phone screen
        f.setSize(new Dimension(720,1280));
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(new GridLayout(3,1,0,10));
        f.setTitle("House of ec22792");

        //Gold and items
        p1= new JPanel();
        p1.setPreferredSize(new Dimension(720,425));
        //Gold
        gold= new JLabel("Gold: "+ goldTot);
        gold.setHorizontalAlignment(JLabel.LEFT);
        gold.setVerticalAlignment(JLabel.TOP);
        gold.setBackground(Color.orange);
        gold.setOpaque(true);
        p1.add(gold);
        //Items
        itemsString= itemsToString(itemList);
        itemsLabel= new JLabel("Items" + itemsString);
        itemsLabel.setBackground(Color.orange);
        itemsLabel.setOpaque(true);
        p1.add(itemsLabel);

        //Welcome & map
        p2= new JPanel();
        p2.setPreferredSize(new Dimension(720,425));
        welcome= new JLabel("Welcome to the House of ec22792!");
        welcome.setHorizontalAlignment(JLabel.CENTER);
        welcome.setVerticalAlignment(JLabel.CENTER);
        p2.add(welcome);

        map= new JTextArea();
        map.setText(
                " - - - - - - - - - - - - - -\n" +
                        "|   ec22562  |  ec22433    |\n" +
                        " - - - - - - - - - - - - - - - - - - \n" +
                        "|                                   |\n" +
                        " - - - - - - - - - - - - - -        | - - - - -\n" +
                        "|  ec22860   |    ec22792   |       | YOU |\n" +
                        " - - - - - - - - - - - - - - - - - - - - - - - - ");
        p2.add(map);

        //Options
        p3= new JPanel();
        p3.setPreferredSize(new Dimension(720,425));
        JLabel options= new JLabel("Go to:");
        p3.add(options);
        //ec22433
        a= new JButton("ec22433");
        a.addActionListener(this);
        p3.add(a);
        b= new JButton("ec22792");
        b.addActionListener(this);
        p3.add(b);
        c= new JButton("ec22860");
        c.addActionListener(this);
        p3.add(c);
        d= new JButton("ec22562");
        d.addActionListener(this);
        p3.add(d);

        f.add(p1, BorderLayout.NORTH);
        f.add(p2);
        f.add(p3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==a) {dir = new Room_ec22433().visit(v, dir);}
        else if(e.getSource()==b){dir=new Room_ec22792().visit(v,dir);}
        else if(e.getSource()==c){dir=new Room_ec22860().visit(v,dir);}
        else if(e.getSource()==d) {dir=new Room_ec22562().visit(v,dir);}
    }

    public static String itemsToString(HashMap<Item, String> xs){
        itemsString="";
        for (String x: itemList.values()){
            itemsString+=x+ " ,";
        }
        return itemsString;
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
