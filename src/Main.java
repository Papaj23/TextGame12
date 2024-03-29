
import java.util.*;

public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean npc1Done = false;
        boolean npc2Done = false;

        Room bedroom1 = new Room("Small bedroom", "Bedroom with own toilet.");
        Room toilet = new Room("Toilet", "Little, smelly toilet");
        Room hall = new Room("Hall", "Long, messy hall with doors on each wall");
        Room bedroom2 = new Room("Big bedroom", "Bedroom with king size bed");
        Room bathroom = new Room("Bathroom", "For sure it was party here...disgusting!");
        Room livingRoom = new Room("Living room", "Huge living room with mixing console and large speakers");
        Room kitchen = new Room("Kitchen", "It's full of food leftovers...");
        Room exit = new Room("Exit", "Finally you can get some fresh air!");

        Item breathalyser = new Item("breathalyser", "You can now check % in your blood");
        Item money = new Item("money", "Lovely money!");
        Item phone = new Item("mobile phone", "Your lost phone, now you can call TAXI!");
        Item drivingLicense = new Item("driving license", "It belongs to you! You can back by car now!");
        Item firstAidKit = new Item("first aid kit", "It has some vitamins inside");
        Item cat = new Item("cat", "Meoooow! Meeeow!");
        Item vodka = new Item("vodka", "100ml of finest polish vodka");
        Item key1 = new Item("key", "Small, single key");
        Item key2 = new Item("keys", "Bunch of keys");
        Item chineseSoup = new Item("chinese soup", "Great for regeneration!");
        Item electrolytes = new Item("bottle of electrolytes", "for sure it helps!");

        Character npc1 = new NPC1("Stranger", "Why is he in bed with you...?");
        Character npc2 = new NPC2("Plodder", "He looks smart...but he is drunk");
        Character npc3 = new NPC3("Gambler", "Funny, tricky guy");
        Character npc4 = new NPC4("Cat girl", "She is looking for something...");

        bedroom1.addExit("north", toilet);
        bedroom1.addExit("east", hall);
        bedroom1.addItem(breathalyser, 1);
        bedroom1.addCharacter(npc1);

        toilet.addExit("south", bedroom1);
        toilet.addItem(vodka, 1);
        toilet.addItem(key1, 1);
        toilet.addCharacter(npc2);


        hall.addExit("north", bathroom);
        hall.addExit("east", livingRoom);
        hall.addExit("south", bedroom2);
        hall.addExit("west", bedroom1);
        hall.addItem(money, 5);
        hall.addItem(phone, 1);


        bedroom2.addExit("north", hall);
        bedroom2.addItem(drivingLicense, 1);
        bedroom2.addCharacter(npc3);

        bathroom.addExit("south", hall);
        bathroom.addItem(firstAidKit, 1);
        bathroom.addItem(cat, 1);

        livingRoom.addExit("north", kitchen);
        livingRoom.addExit("east", exit);
        livingRoom.addExit("west", hall);
        livingRoom.addItem(money, 5);
        livingRoom.addItem(electrolytes, 1);

        kitchen.addExit("south", livingRoom);
        kitchen.addItem(key2, 1);
        kitchen.addItem(chineseSoup, 1);
        kitchen.addCharacter(npc4);

        bedroom1.getNextRoom("east").roomIsLocked = true;


        System.out.println("Hello, what's your name?");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName, 0.5);

        System.out.println("Welcome to game " + playerName + "! Let's start!");
        System.out.println("Here is command list, which will help you in this adventure: \n");
        commandList();
        player.setCurrentRoom(bedroom1);
        player.showPossibleWays();

        System.out.println("\n" + playerName + " you wake up in bedroom, but...It's not yours!" + "\n" + "Someone is sleeping near you...");
        System.out.println("You got big headache and don't remember what happened last night...");
        System.out.println("Your backpack is on the ground, but it's empty!");




        while (true) {

            if (!npc1Done && npc1.talkedTo && !player.getBackpack().getItemsInBackpack().containsKey(breathalyser)) {
                player.putInBackpack(breathalyser, 1);
                npc1Done = true;
            }
            if (!npc2Done && npc2.talkedTo && !player.getBackpack().getItemsInBackpack().containsKey(key1)) {
                player.putInBackpack(key1, 1);
                npc2Done = true;
            }

            System.out.println("What do you want to do?");
            String command = getPlayerInput(scanner);
            useCommand(command, player);
        }

    }



    private static String getPlayerInput(Scanner scanner){
        System.out.println(">");
        String command = scanner.nextLine();
        return command;
    }

    private static void useCommand(String command, Player player) {
        if (command.isEmpty()) {
            System.out.println("Empty input, use command from list!");
        } else {

            String command1 = command.toLowerCase();
            String[] splitted = command1.split(" ");

            // Komenda poruszania się
            if (splitted[0].equals("go") && splitted.length >= 2) {
                player.move(splitted[1]);
                // komenda dzwonienia po taxi
            } else if(splitted[0].equals("call") && splitted[1].equals("taxi")){
                if (player.getBackpack().getItemsInBackpack().containsKey(??????????????? phone) {
                    if (player.getBackpack().getItemsInBackpack().containsKey(?????? money) && player.getBackpack().getItemsInBackpack().get(????? money) >= 30){
                        System.out.println("You called a taxi... Your taxi will arrive in 5 minutes!");
                        System.out.println("Congratulations! You have finished the game!");
                    } else {
                        System.out.println("You don't have enough money to call a taxi! It will cost 30$.");
                    }
                } else {
                    System.out.println("You don't have any mobile phone to do that!");
                }
            }
            //Komenda sprawdzania zawartości plecaka i stanu upojenia
            else if (splitted[0].equals("check") && splitted[1].equals("backpack")) {
                player.showBackpack();
            }
             //Komenda opisu aktualnego pomieszczenia
            else if(splitted[0].equals("get") && splitted[1].equals("description")){
                player.getCurrentRoomDescription();
            }//Komenda rozmawiania z postaciami
            else if(splitted[0].equals("talk") && splitted[1].equals("to")){
                String npcName = splitted[2];

                for (Character npc : player.getCurrentRoom().getNPCs()) {
                    if (npc.getName().equalsIgnoreCase(npcName)) {
                        player.talkTo(npc);
                        return;
                    }
                } System.out.println("There is no NPC named '" + npcName + "' in this room.");

            }
            //komenda dodawania przedmiotów do plecaka
            else if (splitted[0].equals("add") && splitted.length <= 5) {
                    if (splitted.length == 3) {
                        if(splitted[1].equals("breathalyser") || splitted[1].equals("key")) {
                            System.out.println("You can't take it! You should speak to someone first! ");
                        } else {
                            int quantity = 0;
                            try {
                                quantity = Integer.parseInt(splitted[2]);
                            } catch (NumberFormatException e){
                                System.out.println("Bad input format!");
                                return;
                            }
                            if (quantity > 0) {
                                String itemName = splitted[1];
                                Item itemToAdd = null;

                                for (Item item : player.getCurrentRoom().getItemsInRoom().keySet()) {
                                    if (item.getName().trim().equalsIgnoreCase(itemName)) {
                                        itemToAdd = item;
                                        break;
                                    }
                                }
                                if (itemToAdd != null) {
                                    player.putInBackpack(itemToAdd, quantity);
                                } else {
                                    System.out.println("No item here!");
                                }
                            } else {
                                System.out.println("Quantity must be grater than 0!");
                            }
                        }

                    } else if (splitted.length == 4) {
                        int quantity = 0;
                        try{
                            quantity = Integer.parseInt(splitted[3]);
                        } catch (NumberFormatException e) {
                            System.out.println("Bad input format");
                            return;
                       }
                        if (quantity > 0) {

                            String[] itemName = new String[]{splitted[1] + " " + splitted[2]};
                            Item itemToAdd = null;

                            for (Item item : player.getCurrentRoom().getItemsInRoom().keySet()) {
                                if (item.getName().trim().equalsIgnoreCase(itemName[0])) {
                                    itemToAdd = item;
                                    break;
                                }
                            }
                            if (itemToAdd != null) {
                                player.putInBackpack(itemToAdd, Integer.parseInt(splitted[3]));
                            } else {
                                System.out.println("No item here!");
                            }
                        } else {
                            System.out.println("Quantity must be grater than 0!");
                        }
                    } else if (splitted.length == 5) {
                        int quantity = 0;
                        try{
                         quantity = Integer.parseInt(splitted[4]);
                        } catch(NumberFormatException e){
                            System.out.println("Bad input format!");
                            return;
                        }
                        if (quantity > 0) {

                            String[] itemName = new String[]{splitted[1] + " " + splitted[2] + " " + splitted[3]};
                            Item itemToAdd = null;

                            for (Item item : player.getCurrentRoom().getItemsInRoom().keySet()) {
                                if (item.getName().trim().equalsIgnoreCase(itemName[0])) {
                                    itemToAdd = item;
                                    break;
                                }
                            }
                            if (itemToAdd != null) {
                                player.putInBackpack(itemToAdd, Integer.parseInt(splitted[4]));
                            } else {
                                System.out.println("No item here!");
                            }
                        } else {
                            System.out.println("Quantity must be grater than 0!");
                        }
                    } else {
                        System.out.println("Bad input command! Correct format is: add + item name + quantity");
                    } // Komenda używania przedmiotów
            } else if (splitted[0].equals("use") && splitted.length <= 5){
                try {
                    if (splitted.length == 3) {

                        int quantity = Integer.parseInt(splitted[2]);

                        if (quantity > 0) {
                            String itemName = splitted[1];
                            Item itemToUse = null;

                            for (Item item : player.getBackpack().getItemsInBackpack().keySet()) {
                                if (item.getName().trim().equalsIgnoreCase(itemName)) {
                                    itemToUse = item;
                                    break;
                                }
                            }
                            if (itemToUse != null) {
                                if (splitted[1].equals("breathalyser")) {
                                    player.getDrunkLevel();
                                } else {
                                    if(splitted[1].equals("key") && splitted[2].equals("1") && player.getCurrentRoom().getNextRoom("east").getName().equals("Hall")){
                                    System.out.println("You used key on the door...It works! You can pass now.");
                                    player.getCurrentRoom().getNextRoom("east").roomIsLocked = false;
                                    }
                                    if(splitted[1].equals("vodka") ){
                                    player.increaseDrunkLevel(0.5);

                                }
                                player.useItemFromBackpack(itemToUse, quantity);
                                }
                            } else {
                                System.out.println("No item in your backpack!");
                            }
                        } else {
                            System.out.println("Quantity must be grater than 0!");
                        }
                    } else if (splitted.length == 4) {

                        int quantity = Integer.parseInt(splitted[3]);

                        if (quantity > 0) {

                            String[] itemName = new String[]{splitted[1] + " " + splitted[2]};
                            Item itemToAdd = null;

                            for (Item item : player.getBackpack().getItemsInBackpack().keySet()) {
                                if (item.getName().trim().equalsIgnoreCase(itemName[0])) {
                                    itemToAdd = item;
                                    break;
                                }
                            }
                            if (itemToAdd != null) {
                                player.putInBackpack(itemToAdd, Integer.parseInt(splitted[3]));
                            } else {
                                System.out.println("No item in your backpack!");
                            }
                        } else {
                            System.out.println("Quantity must be grater than 0!");
                        }
                    } else if (splitted.length == 5) {

                        int quantity = Integer.parseInt(splitted[4]);
                        if (quantity > 0) {

                            String[] itemName = new String[]{splitted[1] + " " + splitted[2] + " " + splitted[3]};
                            Item itemToAdd = null;

                            for (Item item : player.getBackpack().getItemsInBackpack().keySet()) {
                                if (item.getName().trim().equalsIgnoreCase(itemName[0])) {
                                    itemToAdd = item;
                                    break;
                                }
                            }
                            if (itemToAdd != null) {
                                player.putInBackpack(itemToAdd, Integer.parseInt(splitted[4]));
                            } else {
                                System.out.println("No item in your backpack!");
                            }
                        } else {
                            System.out.println("Quantity must be grater than 0!");
                        }
                    }

                } catch (NumberFormatException e){
                    System.out.println("Invalid quantity format. Please enter a valid number");

                }
            }
        }

    }

    private static void commandList() {

        List<String> commandList = new ArrayList<>();
        commandList.add("go north - move to next room located on north");
        commandList.add("go east - move to next room located on east");
        commandList.add("go south - move to next room located on south");
        commandList.add("go west - move to next room located on west");
        commandList.add("check backpack - check items inside your backpack ");
        commandList.add("add (item name and quantity) - put item in backpack");
        commandList.add("use (item name and quantity)  - you use item which you found");
        commandList.add("call taxi - you can call taxi to get home");
        commandList.add("talk to (NPC name) - start conversation");
        commandList.add("get description - to show description of current room");
        commandList.add("yes - you accept");
        commandList.add("no - you don't accept");


        System.out.println("COMMAND LIST:");

        for (String command : commandList) {
            System.out.println(command);
        }
    }
}
