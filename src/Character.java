public class Character implements Talk {

    private String name;
    private Room room;
    private String description;
    boolean talkedTo;


    public Character(String name, String description){
        this.name = name;
        this.description = description;
        this.talkedTo = false;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public void startConversation() {

    }

    @Override
    public void conversation() {


    }

    @Override
    public void endConversation() {
        talkedTo = true;

    }
}



