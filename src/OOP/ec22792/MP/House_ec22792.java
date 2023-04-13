package OOP.ec22792.MP;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.PrintStream;
import java.io.InputStream;

class House_ec22792 extends House implements ActionListener {
    public JFrame f= new JFrame();
    public JPanel p1;
    public JPanel p2;
    public JPanel p3;
    public static JLabel gold;
    public static int goldTot= 0;
    public static JLabel itemsLabel;
    public static HashMap<Item,String> itemList= new HashMap<>();
    public static String itemsString="";
    public JLabel welcome;
    public JTextArea map;
    public static Visitor v= new GUIVisitor_ec22792();
    public static Direction dir= new Direction();
    public HashMap<Room, String> floor1= new HashMap<>();
    private final JButton floor1Btn;
    public HashMap<Room, String> floor2= new HashMap<>();
    private final JButton floor2Btn;
    private Room r1;
    private Room r2;
    private Room r3;
    private Room r4;
    private Room current;
    private static final PrintStream ps= System.out;
    private static final InputStream is= System.in ;
    private Floor1 f1;
    private boolean f1created=false;

    //main to test
   /* public static void main(String[] args){
        OOP.ec22792.A8.House h= new OOP.ec22792.A8.House_ec22792();
        IOVisitor v= new IOVisitor(ps, is);
        OOP.ec22792.A8.Direction d= OOP.ec22792.A8.Direction.TO_WEST;
        d= h.visit(v,d);
    } */
    
    //Constructor
    House_ec22792() {
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
        map.setText("");
        p2.add(map);

        //Options
        p3= new JPanel();
        p3.setPreferredSize(new Dimension(720,425));
        JLabel options= new JLabel("Go to:");
        p3.add(options);
        //First floor
        f1=null;
        floor1Btn= new JButton("Ground floor");
        floor1Btn.addActionListener(this);
        p3.add(floor1Btn);
        floor2Btn= new JButton("First floor");
        floor2Btn.addActionListener(this);
        p3.add(floor2Btn);

        f.add(p1, BorderLayout.NORTH);
        f.add(p2);
        f.add(p3);
        f.pack();
        f.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==floor1Btn){
            Floor1 f1= new Floor1();
            floor1Btn.setEnabled(false);
        }
        else if(e.getSource()==floor2Btn){
            Floor2 f2=new Floor2();}
    }

    public static String itemsToString(HashMap<Item, String> xs){
        itemsString="";
        for (String x: itemList.values()){
            itemsString = itemsString + (x + " ,");
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
            if ((d== Direction.TO_SOUTH) || (d== Direction.FROM_SOUTH)) {
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
    }
    
    //Get the user's choice
    char getChoice(String choice, char [] options){

        System.out.println(choice + " " + Arrays.toString(options));
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
            if (x == choice) {
                valid = true;
                break;
            }
        }
        return valid;
    }
    
    //inputString
    String inputString() {
        Scanner sc= new Scanner(System.in);
        return sc.nextLine();
    }
    
    
}
