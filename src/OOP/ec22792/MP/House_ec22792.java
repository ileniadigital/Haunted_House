package OOP.ec22792.MP;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

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
    public static Visitor v= new GUIVisitor_ec22792();
    public static Direction dir= new Direction();
    public static HashMap<String, Room> floor1= new HashMap<>();
    private final JButton floor1Btn;
    public HashMap<String, Room> floor2= new HashMap<>();
    private final JButton floor2Btn;
    private HashMap<String, Room> floorRooms;
    public static Room current;

    //Constructor
    House_ec22792() {
        //Create GUI
        /*Rooms on the first floor*/
        //My room
        floor1.put("ec22792", new Room_ec22792() ) ;
        //Rooms chosen at random from A5 contributions
        floor1.put("ec2433",new Room_ec22433()) ;
        floor1.put("ec22562", new Room_ec22562());
        floor1.put("ec22860", new Room_ec22860());

        /*Rooms on second floor*/
        floor2.put("ec221013", new Room_ec221013());
        floor2.put("ec221025", new Room_ec221025());
        floor2.put("ec22558", new Room_ec22558());
        floor2.put("ec22777", new Room_ec22777());

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


        //Options
        p3= new JPanel();
        p3.setPreferredSize(new Dimension(720,425));
        JLabel options= new JLabel("Go to:");
        p3.add(options);
        //First floor
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
            new Floor1();
            floor1Btn.setEnabled(false);
            floorRooms=floor1;
        }
        else if(e.getSource()==floor2Btn){
            new Floor2();
            floor2Btn.setEnabled(false);
            floorRooms=floor2;
        }
        visit(v,dir);
    }


    public static String itemsToString(HashMap<Item, String> xs){
        itemsString="";
        for (String x: xs.values()){
            itemsString += (x + " ,");
        }
        return itemsString;
    }
    @Override
    //visit method
    public Direction visit(Visitor v, Direction d) {
        v.tell("You are in "+ floorRooms.get(current));
        Room[] rooms= new Room[floorRooms.size()];
        floorRooms.values().toArray(rooms);
        Room r1 = rooms[0];
        Room r2 = rooms[1];
        Room r3 = rooms[2];
        Room r4 = rooms[3];

        //Always starts from r1
        current=r1;
        char[] options = {'a', 'b', 'c', 'd'};
        v.giveGold(5);

        //If direction returned is SOUTH, the user leaves
        if ((d == Direction.TO_SOUTH) || (d == Direction.FROM_SOUTH)) {
            v.tell("Your journey ends here");
            v.giveGold(10);
            v.tell("Now OUT OF HERE!");
        } else {
            v.tell("Where do you want go?");
            char choice = v.getChoice("a) North b) South c) East d) West?", options);
            if (choice == 'a') {
                if (current == r1) {
                    current = r2;
                } else if ((current == r2) || (current == r3)) {
                    v.tell("You cannot go this way");
                    current = r1;
                } else {
                    current = r3;
                }
            } else if (choice == 'b') {
                if ((current == r1) || (current == r4)) {
                    v.tell("You cannot go this way");
                    current = r3;
                } else if (current == r2) {
                    current = r1;
                } else if (current == r3) {
                    current = r4;
                }
            } else if (choice == 'c') {
                if ((current == r1) || (current == r2)) {
                    v.tell("You cannot go this way");
                    current = r4;
                } else if (current == r3) {
                    current = r2;
                } else if (current == r4) {
                    current = r1;
                }
            } else {
                if ((current == r4) || (current == r3)) {
                    v.tell("You cannot go this way");
                    v.tell("Putting you somewhere else...");
                    current = r1;
                } else if (current == r1) {
                    current = r4;
                } else if (current == r2) {
                    current = r3;
                }
            }
        }

        House_ec22792.dir = current.visit(v, House_ec22792.dir);
        return House_ec22792.dir;
    }
    

    //Welcome message
//    void welcome(Visitor v) {
//        v.tell("Welcome to the OOP.ec22792.A8.House of ec22792!");
//        v.tell("Here is what it looks like:");
//        v.tell(" - - - - - - - - - - - - - -");
//        v.tell("|            |              |");
//        v.tell("|     ec     |      ec      |");
//        v.tell("|    22562   |    22433     |");
//        v.tell("|            |              |");
//        v.tell(" - - - - - - - - - - - - - - - - - - ");
//        v.tell("|                                   |");
//        v.tell(" - - - - - - - - - - - - - -        |");
//        v.tell("|            |              |       | - - - - - ");
//        v.tell("|      ec    |     ec       |       |    You    |");
//        v.tell("|    22860   |    22792     |       |    are    |");
//        v.tell("|            |              |       |    here   |");
//        v.tell("|            |              |       | - - - - -");
//        v.tell(" - - - - - - - - - - - - - - - - - - - - - - - - ");
//    }

     //Give gold
//    int giveGold( int newGold, int total, Visitor v){
//        v.tell("You have received " + newGold + " gold");
//        total= total+newGold;
//        v.tell("You have " + total + " gold");
//        return total;
//    }

    //Tell the user something
//    void tell(String s) {
//        System.out.println(s);
//    }
    
    //Get the user's choice
//    char getChoice(String choice, char [] options){
//
//        System.out.println(choice + " " + Arrays.toString(options));
//        String uChoice = inputString();
//        uChoice= uChoice.toLowerCase();
//        char uChar= uChoice.charAt(0);
//
//        boolean valid= check(uChar, options);
//
//        while (!valid){
//            tell("Invalid choice! Try again");
//            uChoice=inputString();
//            uChoice= uChoice.toLowerCase();
//            uChar= uChoice.charAt(0);
//            valid=check(uChar, options);
//        }
//
//        return uChar;
//    }
//
//    //Check if a choice is valid
//    boolean check(char choice, char[] options){
//        boolean valid= false;
//        for (char x : options) {
//            if (x == choice) {
//                valid = true;
//                break;
//            }
//        }
//        return valid;
//    }
    
    //inputString
//    String inputString() {
//        Scanner sc= new Scanner(System.in);
//        return sc.nextLine();
//    }



}
