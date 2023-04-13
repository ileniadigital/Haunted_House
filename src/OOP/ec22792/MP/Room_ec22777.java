package OOP.ec22792.MP;

public class Room_ec22777 extends Room {

    static final Item FLASHLIGHT = new Item("Flashlight");
    static final Item JOURNAL = new Item("Journal");
    static final Item BOOK = new Item("Book");

    public Direction visit(Visitor visitor, Direction direction) {
        visitor.tell("As you enter the room, you feel a chill down your spine. The room is dimly lit, with cobwebs hanging from the ceiling. You spot a dusty journal, a book with a worn-out cover, and a flashlight on the ground.");

        char[] options = { '1', '2', '3' };
        char choices = visitor.getChoice("What would you like to do? 1) Examine the journal  2) Inspect the book  3) Pick up the flashlight", options);
        int coins = 0;
        boolean hasFlashlight = visitor.hasEqualItem(FLASHLIGHT);

        if (choices == '1' && !hasFlashlight) {
            visitor.tell("You open the journal and find a map that leads to a treasure hidden in the room. You also spot some gold coins tucked between the pages.");
            visitor.giveItem(JOURNAL);
            visitor.giveGold(3);
            coins = coins + 3;
        } else if (choices == '2') {
            if (coins > 0) {
                visitor.tell("As you inspect the book, a swarm of bats flies out from within the pages, causing you to drop some coins. One of the coins rolls under the bed and disappears from sight.");
                visitor.takeGold(1);
                coins = coins - 1;
            }

            if (hasFlashlight) {
                visitor.tell("You use the flashlight to illuminate the bookshelves and notice a hidden compartment behind one of them. You find a key inside that unlocks a chest in the room.");
                visitor.giveItem(BOOK);
            }

        } else if (choices == '3') {
            visitor.tell("You pick up the flashlight, and as you shine it around the room, you notice more coins scattered on the ground.");
            visitor.giveGold(2);
            coins = coins + 2;
        } else {
            visitor.tell("You decided not to do anything.");
        }
        return direction;
    }
}