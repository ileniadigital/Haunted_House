package OOP.ec22792.A8;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUIVisitor_ec22792 implements Visitor{
    public JFrame f= new JFrame();
    public JPanel p;
    public JLabel gold;
    public int goldTot= 0;
    public JLabel items;
    public List<Item> itemList= new ArrayList<Item>();


    //Constructor
    public GUIVisitor_ec22792(JFrame f){
        //Setting frame to size of phone screen
        f.setSize(new Dimension(720,1280));
        p= new JPanel();
        gold= new JLabel("You have " + goldTot +" gold");
        items= new JLabel("You have X items");

        p.add(gold);
        p.add(items);
        f.add(p);

    }
    @Override
    public void tell(String messageForVisitor) {
        JOptionPane.showMessageDialog(null, messageForVisitor);
    }

    @Override
    public char getChoice(String descriptionOfChoices, char[] arrayOfPossibleChoices) {
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
