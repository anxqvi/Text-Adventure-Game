import java.util.Random;
public class Coin extends Item
{
    private int chanceOfDistraction;

    public Coin()
    {
        super(-1, 0, 0, 0);
        Random random = new Random();
        int num = random.nextInt(10) + 1;
        if (num <=3) {
            chanceOfDistraction = 3;
        }
        else if (num <=7) {
            chanceOfDistraction = 5;
        }
        else {
            chanceOfDistraction = 7;
        }
    }

    public boolean distract(boolean distracted) {
        Random random = new Random();
        if (distracted = true) {
            return true;
        }
        
        else {
            if ((random.nextInt(10) + 1) >= chanceOfDistraction) {
                return true;
            }
            
            else {
                return false;
            }
        }
    }
    
    public void printDescription() {
        System.out.println("A single coin is lying on the floor.");
    }
}
