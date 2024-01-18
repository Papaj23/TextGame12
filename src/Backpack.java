import java.util.HashMap;
import java.util.Map;

public class Backpack {

    private Map<Item, Integer> itemsInBackpack;

    public Backpack() {
        this.itemsInBackpack = new HashMap<>();

    }

    public Map<Item, Integer> getItemsInBackpack() {
        return itemsInBackpack;
    }


    public void addItems(Item item, int quantity) {

        if(itemsInBackpack.containsKey(item)){
            int currentQuantity = itemsInBackpack.get(item);
            int newQuantity = currentQuantity + quantity;
            itemsInBackpack.put(item, newQuantity);
        } else {
            itemsInBackpack.put(item,quantity);
        }
    }

    public void useBackpackItem(Item item, int quantity) {
        int currentQuantity = itemsInBackpack.get(item);
        if (!itemsInBackpack.containsKey(item)) {
            System.out.println("YOU DON'T HAVE IT IN YOUR BACKPACK.");
        } else {
            if (currentQuantity - quantity == 0) {
                System.out.println("You used " + item + " from backpack, you got no more left.");
                itemsInBackpack.remove(item);
            } else {
                if (currentQuantity - quantity > 0) {
                    int newQuantity = currentQuantity - quantity;
                        itemsInBackpack.put(item, newQuantity);
                    System.out.println("You used " + item + " from backpack, you got " + newQuantity + " left");
                }
            }
        }
    }

    public void showItems() {
        if (itemsInBackpack.isEmpty()) {
            System.out.println("YOU GOT NOTHING IN YOUR BACKPACK!");

        } else {
            System.out.println("ITEMS INSIDE YOUR BACKPACK:");
            for (Map.Entry<Item, Integer> items : itemsInBackpack.entrySet()) {
                Item item = items.getKey();
                int quantity = items.getValue();
                System.out.println(item + " QUANTITY: " + quantity);
            }
        }
    }
}


