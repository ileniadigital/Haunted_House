package OOP.ec22792.A8;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class myVisitor implements Visitor {
    public JFrame f= new JFrame();
    public HashMap<Item,String> items= new HashMap<Item,String>();
    public int gold;
    public DefaultListModel<Item> model= new DefaultListModel<>();
    public JList<Item> itemList= new JList<>();


    @Override
    public void tell(String messageForVisitor) {
        JOptionPane.showMessageDialog(null, messageForVisitor);
    }

    @Override
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

    @Override
    public boolean giveItem(Item itemGivenToVisitor) {
        if (!((hasEqualItem(itemGivenToVisitor)) || (hasIdenticalItem(itemGivenToVisitor)))){
            GUIVisitor_ec22792.itemList.put(itemGivenToVisitor, itemGivenToVisitor.toString());
            GUIVisitor_ec22792.itemsString=GUIVisitor_ec22792.itemsToString(GUIVisitor_ec22792.itemList);
            GUIVisitor_ec22792.itemsLabel.setText("Items: " + GUIVisitor_ec22792.itemsString);
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
    public boolean hasEqualItem(Item itemToCheckFor) {
        if (items.containsValue(itemToCheckFor.toString())){
            return true;
        }
        return false;
    }

    @Override
    public void giveGold(int numberOfPiecesToGive) {
            tell("You have received " + numberOfPiecesToGive+ " gold");
            GUIVisitor_ec22792.goldTot= GUIVisitor_ec22792.goldTot+numberOfPiecesToGive;
            GUIVisitor_ec22792.gold.setText("Gold:" + Integer.toString(GUIVisitor_ec22792.goldTot));
            return;
    }

    @Override
    public int takeGold(int numberOfPiecesToTake) {
        tell("You lose " + numberOfPiecesToTake + "gold");
        GUIVisitor_ec22792.goldTot=GUIVisitor_ec22792.goldTot-numberOfPiecesToTake;
        GUIVisitor_ec22792.gold.setText("Gold:" + Integer.toString(GUIVisitor_ec22792.goldTot));
        return gold;
    }
}
