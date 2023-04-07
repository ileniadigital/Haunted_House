package OOP.ec22792.A8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUIVisitor_ec22792 implements Visitor, ActionListener {
    public JFrame f= new JFrame();
    public JPanel p1;
    public JPanel p2;
    public JPanel p3;
    public JLabel gold;
    public int goldTot= 0;
    public JLabel items;
    public List<Item> itemList= new ArrayList<Item>();
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
        gold= new JLabel("You have " + goldTot +" gold");
        gold.setHorizontalAlignment(JLabel.LEFT);
        gold.setVerticalAlignment(JLabel.TOP);
        gold.setBackground(Color.orange);
        gold.setOpaque(true);
        p1.add(gold);
        //Items
        items= new JLabel("You have X items");
        items.setHorizontalAlignment(JLabel.LEFT);
        items.setVerticalAlignment(JLabel.TOP);
        items.setBackground(Color.orange);
        items.setOpaque(true);
        p1.add(items);

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
    public char getChoice(String descriptioOfChoices, char[] arrayOfPossibleChoices) {
        return 0;
    }

    @Override
    public boolean giveItem(Item itemGivenToVisitor) {
        return false;
    }

    @Override
    public boolean hasIdenticalItem(Item itemToCheckFor) {
        return false;
    }

    @Override
    public boolean hasEqualItem(Item itemToCheckFor) {
        return false;
    }

    @Override
    public void giveGold(int numberOfPiecesToGive) {

    }

    @Override
    public int takeGold(int numberOfPiecesToTake) {
        return 0;
    }
}
