package OOP.ec22792.MP;

import javax.swing.*;
import java.util.*;

public class GUIVisitor_ec22792 implements Visitor {
    public static JLabel gold;
    public static int goldTot= 0;
    public static JLabel itemsLabel;
    public static HashMap<Item,String> itemList= new HashMap<Item,String>();
    public static String itemsString="";
    public Visitor v= new myVisitor();
    public Direction dir= new Direction();

    //Constructor
    public GUIVisitor_ec22792(){}


    @Override
    public void tell(String messageForVisitor) {
        JOptionPane.showMessageDialog(null, messageForVisitor);
    }

    @Override
    public boolean giveItem(Item itemGivenToVisitor) {
        if (!((hasEqualItem(itemGivenToVisitor)) || (hasIdenticalItem(itemGivenToVisitor)))){
            itemList.put(itemGivenToVisitor, itemGivenToVisitor.toString());
            itemsString=House_ec22792.itemsToString(itemList);
            itemsLabel.setText("Items: " + itemsString);
            return true;
        }
        return false;

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

