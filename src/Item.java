import java.util.Objects;

public class Item {

    private String name;
    private String description;
    private int quantity;

    public Item(String name){
        this.name = name;
    }

    public Item(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Item(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return name + " (" + description +")";
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }
}


