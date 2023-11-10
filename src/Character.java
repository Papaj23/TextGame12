public class Character implements Talk {

    private String name;
    private Room room;
    private String description;
    boolean talkedTo = false;

    public Character(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }


    public void startTalk(){
        System.out.println("conversation started");

    }
}



