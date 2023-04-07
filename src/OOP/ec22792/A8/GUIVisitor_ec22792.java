package OOP.ec22792.A8;

public class GUIVisitor_ec22792 implements Visitor{
    @Override
    public void tell(String messageForVisitor) {

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
