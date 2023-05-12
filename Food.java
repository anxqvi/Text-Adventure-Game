import java.util.Random;
import java.util.Scanner;
public class Food extends Item
{
    private boolean bloated;
    private String name;

    public Food()
    {
        bloated = false;
        Random random = new Random();
        int num = random.nextInt(10) + 1;
        if (num <=3) {
            name = "spinach";
        }
        else if (num <=6) {
            name = "burger";
        }
        else {
            name = "large pizza";
        }
            
    }
    
    public String getName() {
        
        return name;
        
    }
    
    public boolean getBloated() {
        
        return bloated;
        
    }
    
    public void stats() {
        
        if (name.equals("large pizza")) {
            setAttackLevelModifier(-2);
            setHealthModifier(4);
            bloated = true;
            System.out.println("You are bloated and fight worse than before.");
        }
        
        if (name.equals("burger")) {
            setAttackLevelModifier(-1);
            setHealthModifier(3);
            bloated = true;
            System.out.println("You are bloated and fight a little bit worse than before.");
        }
        
        if (name.equals("spinach")) {
            setAttackLevelModifier(2);
            setHealthModifier(2);
            bloated = false;
            System.out.println("You feel good and ready for a fight.");
        }
    }
    
    public void printDescription() {
        if (name.equals("large pizza")) {
            System.out.println("An unfinished large pizza is sitting on a table.");
        }
        
        if (name.equals("burger")) {
            System.out.println("A half eaten burger is sitting on a table.");
        }
        
        if (name.equals("spinach")) {
            System.out.println("An unopened pack of spinach is sitting on a table.");
        }
    }
        
}
