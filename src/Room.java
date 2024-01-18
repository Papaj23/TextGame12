import jdk.swing.interop.SwingInterOpUtils;

import java.util.*;

public class Room {

    private String name;
    private String roomDescription;
     private HashMap<Item, Integer> itemsInRoom;
     private Map<String, Room> exits = new HashMap<>();
    private List<Character> NPCs = new ArrayList<>();
    boolean roomIsLocked;

    public Room(String name, String roomDescription){
        this.name = name;
        this.roomDescription = roomDescription;
        this.itemsInRoom = new HashMap<>();
        this.exits = new HashMap<>();
        this.roomIsLocked = false;
    }

    public Map<String, Room> getExits() {
        return exits;
    }

    public HashMap<Item, Integer> getItemsInRoom() {
        return itemsInRoom;
    }

    public String getName() {
        return name;
    }

    public void getDescription() {
        System.out.println("ROOM: " + this.name + "\nDESCRIPTION: " + this.roomDescription);
    }

    public void addItem(Item item, int quantity ){
        itemsInRoom.put(item,quantity);
    }


    public void removeItem(Item item, int quantity){

        if(itemsInRoom.containsKey(item)){
            int currentQuantity = itemsInRoom.get(item);
            if(currentQuantity >= quantity){
                currentQuantity -= quantity;
                if(currentQuantity == 0){
                    itemsInRoom.remove(item);
                    System.out.println("No more " + item.getName() + " in this room!");
                } else {
                    itemsInRoom.put(item, currentQuantity);
                }
            }
        }

    }

    public void addCharacter(Character npc){
        NPCs.add(npc);
    }

    public List<Character> getNPCs() {
        return NPCs;
    }

    public boolean NPCtalkedTo(Character npc){
        return npc.talkedTo;
    }

    public void addExit(String direction, Room room){
        exits.put(direction, room);
    }

    public String getExitList(){

        StringBuilder exitList = new StringBuilder("POSSIBLE EXITS:\n");
        for(String direction : exits.keySet()){
            exitList.append(direction).append(" ");
        }
        return exitList.toString();
    }


        public void getItemsList() {

            System.out.println("ITEMS IN " + name.toUpperCase(Locale.ROOT) + ":");
            if (itemsInRoom.isEmpty()) {
                System.out.println("No more items in this room!");
            } else {
                for (Map.Entry<Item, Integer> entry : itemsInRoom.entrySet()) {
                    Item item = entry.getKey();
                    int quantity = entry.getValue();
                    System.out.println(item + " QUANTITY: " + quantity);
                }
            }
        }


        public Room getNextRoom(String command){

        boolean ways = this.exits.containsKey(command);
        if(ways){
           return this.exits.get(command);
       } else {
           return null;
       }
    }


}
