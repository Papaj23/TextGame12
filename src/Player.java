
import java.util.HashMap;
import java.util.Scanner;

public class Player {

    private String name;
    private double drunkLevel;
    private Backpack backpack;
    private Room currentRoom;
    private Character character;



    public Player(String name) {
        this.name = name;
        this.backpack = new Backpack();

    }

    public Player(String name, double drunkLevel) {
        this.name = name;
        this.drunkLevel = drunkLevel;
        this.backpack = new Backpack();

    }

    public Backpack getBackpack() {
        return backpack;
    }

    public String getName() {
        return name;
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public void getCurrentRoomDescription() {
        this.currentRoom.getDescription();
        showCurrentRoomItems();
        showPossibleWays();
    }

    public void showPossibleWays() {
        System.out.println(this.currentRoom.getExitList());
    }

    public boolean move(String command) {
        Room nextRoom = this.currentRoom.getNextRoom(command);

        if (nextRoom != null) {
            if (!nextRoom.roomIsLocked) {
                this.currentRoom = nextRoom;
                getCurrentRoomDescription();
            } else {
                System.out.println("The door is locked! You need a key to unlock it.");
            }
        } else {
            System.out.println("You can't go this way!");
        }

        return false;
    }


    public double getDrunkLevel() {

        System.out.println(name + " your drunk level is: " + drunkLevel);
        return drunkLevel;
    }

    public double increaseDrunkLevel(double vol){
        drunkLevel += vol;
        return drunkLevel;
    }

    public void putInBackpack(Item item, int quantity) {
        if (!currentRoom.getItemsInRoom().containsKey(item)) {
            System.out.println("Item is not available in this room!");
        } else {
            int roomQuantity = currentRoom.getItemsInRoom().get(item);

            if (roomQuantity >= quantity) {
                backpack.addItems(item, quantity);
                System.out.println("You added " + quantity + " " + item.getName() + " to your backpack.");
                currentRoom.removeItem(item, quantity);

            } else {
                System.out.println("There is not enough " + item.getName() + " in this room! " + "(" + roomQuantity + " left)");
                return;
            }

        }
    }

    public void useItemFromBackpack(Item item, int quantity) {
        backpack.useBackpackItem(item, quantity);
    }

    public void backpackItemList() {
        backpack.getItemsInBackpack();
    }

    public void showBackpack() {
        backpack.showItems();
    }

    public void showCurrentRoomItems() {
        currentRoom.getItemsList();
    }


    public void talkTo(Character NPC) {
        if (!NPC.talkedTo) {
            NPC.startConversation();
            NPC.conversation();
            NPC.endConversation();
        } else {
            System.out.println("You have already talked to this character!");
        }
    }
}



