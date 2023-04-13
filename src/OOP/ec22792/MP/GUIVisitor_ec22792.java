package OOP.ec22792.MP;

import javax.swing.*;

public class GUIVisitor_ec22792 implements Visitor {

    //Constructor
    public GUIVisitor_ec22792(){}


    @Override
    public void tell(String messageForVisitor) {
        JOptionPane.showMessageDialog(null, messageForVisitor);
    }

    @Override
    public boolean giveItem(Item itemGivenToVisitor) {
        if (!((hasEqualItem(itemGivenToVisitor)) || (hasIdenticalItem(itemGivenToVisitor)))){
            House_ec22792.itemList.put(itemGivenToVisitor, itemGivenToVisitor.toString());
            House_ec22792.itemsString=House_ec22792.itemsToString(House_ec22792.itemList);
            House_ec22792.itemsLabel.setText("Items: " + House_ec22792.itemsString);
            return true;
        }
        return false;

    }

    @Override
    public boolean hasEqualItem(Item itemToCheckFor) {
        if (House_ec22792.itemList.containsValue(itemToCheckFor.toString())){
            return true;
        }
        return false;
    }

    @Override
    public boolean hasIdenticalItem(Item itemToCheckFor) {
        if (House_ec22792.itemList.containsKey(itemToCheckFor)){
            return true;
        }
        return false;
    }


    @Override
    public void giveGold(int numberOfPiecesToGive) {
        tell("You have received " + numberOfPiecesToGive+ " gold");
        House_ec22792.goldTot = House_ec22792.goldTot + numberOfPiecesToGive;
        House_ec22792.gold.setText("Gold:" + House_ec22792.goldTot);
    }

    @Override
    public int takeGold(int numberOfPiecesToTake) {
        tell("You lose " + numberOfPiecesToTake + "gold");
        House_ec22792.goldTot=House_ec22792.goldTot-numberOfPiecesToTake;
        House_ec22792.gold.setText("Gold:" + House_ec22792.goldTot);
        return House_ec22792.goldTot;
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

