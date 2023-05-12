import java.util.Random;
public class Bodyguard extends NonPlayer
{
    private boolean distracted;
    
    public Bodyguard(String name)
    {
        super(name, 5, 5);
        Random random = new Random();
        int chance = random.nextInt(10) + 1;
        if (chance > 8) {
            distracted = true;
        }
        
        else {
            distracted = false;
        }
    }
    
    public boolean stopEscape(Character c) {
        
        int stopChance = 10 + super.getAttackLevel();
        
        Random random = new Random();
        if (distracted = true) {
            return false;
        }
        else {
            if ((random.nextInt(stopChance) + 1) >= c.getDefenseLevel()) {
            
            return true;
            }
            else {
            return false;
            }
        }
    }
    
    public boolean isDistracted() {
        
        return distracted;
        
    }
    
    public void distracted() {
        
        distracted = true;
        
    }
    
    public void printDescription() {
        
        if (super.isAlive() == true && super.isConscious() == true) {
            
            if (distracted == true) {
            System.out.println(this.getName() + " appears to be distracted.");
            }
            else {
            System.out.println(this.getName() + " appears to be standing attentive and guarding the room.");
            }
        
            if (getDefenseLevel() >= 5 | getAttackLevel() >= 5 ) {
            System.out.println(this.getName() + " seems pretty strong.");
            }
        
            else if (getDefenseLevel() <= 4 | getAttackLevel() <= 4) {
            System.out.println(this.getName() + " looks like nothing out of the ordinary.");
            }
            
        }
        
        else if (isAlive() == false) {
            System.out.println(this.getName() + " is dead.");
        }
        
        else if (super.hasEscaped() == true) {
            System.out.println(this.getName() + " has escaped.");
        }
        
        else {
            System.out.println(this.getName() + " is knocked out.");
        }
        
    }
}
