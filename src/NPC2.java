import java.util.Random;
import java.util.Scanner;

public class NPC2 extends Character{

    Scanner scanner = new Scanner(System.in);
    boolean puzzleSolved = false;


    public NPC2(String name, String description) {
        super(name, description);
    }



    @Override
    public void startConversation() {
        System.out.println("Hello! You look like you got little hangover ha?");
        System.out.println("There is no better medicine than a little math riddle!");
        System.out.println("I will give you a reward if you solve it correctly...Wanna try?");
        System.out.println("(Type yes or no)");
    }

    @Override
    public void conversation() {

        Random random = new Random();
        String riddle1 = "What is result of 8:4(1+1) = ???";
        String riddle2 = "I got one ear, but I got no head... Who am I?";
        String riddle3 = "When my father was 31, I had 8 years... Now he is twice as old as me... How old am I?";
        String[] riddles =  {riddle1, riddle2, riddle3};

        while (true) {
            String answer1 = scanner.nextLine();
            if (answer1.equals("yes")) {
                System.out.println("Ok! Here's riddle:");
                int randomIndex = random.nextInt(riddles.length);
                String randomRiddle = riddles[randomIndex];
                System.out.println(randomRiddle);
                String answer2 = scanner.nextLine();
                if (randomRiddle.contains(riddle1) && answer2.equals("4") || randomRiddle.contains(riddle2) && answer2.equals("mug") || randomRiddle.contains(riddle3) && answer2.contains("23")) {
                    System.out.println("That's correct! Good job my drunk friend!");
                    System.out.println("Here is the key to the bedroom's door which is locked.");
                    talkedTo = true;
                    break;
                } else{
                    System.out.println("Bad answer! Try again?");
                }

            } else if (answer1.equals("no")){
                System.out.println("Okay, it's your lose! Come back, when you know the answer!");
                talkedTo = false;
                break;
            }
        }
    }


    @Override
    public void endConversation() {
    }
}
