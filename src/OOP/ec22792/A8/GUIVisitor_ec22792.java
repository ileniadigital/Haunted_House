package OOP.ec22792.A8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class GUIVisitor_ec22792 implements Visitor, ActionListener {
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
    public Visitor v= new myVisitor();
    public Direction dir= new Direction();

    //Constructor
    public GUIVisitor_ec22792(JFrame f){
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
        itemsString=itemsToString(itemList);
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

    @Override
    public void tell(String messageForVisitor) {
        JOptionPane.showMessageDialog(null, messageForVisitor);
    }

    @Override
    public boolean giveItem(Item itemGivenToVisitor) {
        if (!((hasEqualItem(itemGivenToVisitor)) || (hasIdenticalItem(itemGivenToVisitor)))){
            itemList.put(itemGivenToVisitor, itemGivenToVisitor.toString());
            itemsString=itemsToString(itemList);
            itemsLabel.setText("Items: " + itemsString);
            return true;
        }
        return false;

    }
    public static String itemsToString(HashMap<Item, String> xs){
        itemsString="";
        for (String x: itemList.values()){
            itemsString+=x+ " ,";
        }
        return itemsString;
    }
    @Override
    public boolean hasEqualItem(Item itemToCheckFor) {
        if (itemList.containsValue(itemToCheckFor.toString())){
            return true;
        }
        return false;
    }

    @Override
    public boolean hasIdenticalItem(Item itemToCheckFor) {
        if (GUIVisitor_ec22792.itemList.containsKey(itemToCheckFor)){
            return true;
        }
        return false;
    }


    @Override
    public void giveGold(int numberOfPiecesToGive) {
        tell("You have received " + numberOfPiecesToGive+ " gold");
        goldTot = goldTot + numberOfPiecesToGive;
        gold.setText("Gold:" + Integer.toString(goldTot));
        return;
    }

    @Override
    public int takeGold(int numberOfPiecesToTake) {
        tell("You lose " + numberOfPiecesToTake + "gold");
        goldTot=goldTot-numberOfPiecesToTake;
        gold.setText("Gold:" + Integer.toString(goldTot));
        return goldTot;
    }

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


}

