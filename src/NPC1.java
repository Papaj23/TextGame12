public class NPC1 extends Character{

    public NPC1(String name, String description) {
        super(name, description);
    }

    @Override
    public void startTalk() {
        System.out.println("HI player");
    }
}
