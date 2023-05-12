import java.util.Random;
public class Staff extends NonPlayer
{
    private int braveness;

    public Staff(String name, int braveness)
    {
        // initialise instance variables
        super(name, 4, 4);
        this.braveness = braveness;
    }
    
    public Staff(String name)
    {
        // initialise instance variables
        super(name, 3, 1);
        Random random = new Random();
        braveness = random.nextInt(10) + 1;
    }
    
    public int getBraveness() {
        
        return braveness;
        
   }
   
    public void setBraveness(int change) {
       
       braveness = change;
       
    }
    
    public boolean escape(Character c, boolean stopEscape) {
        
        if (braveness >= 7) {
            return false;
        }
        else {
            return super.escape(c, stopEscape);
        }
    }
    
    public void printDescription() {
        
        if (super.isAlive() == true && super.isConscious() == true) {
             System.out.println(this.getName() + " appears to be cleaning the room.");
             if (super.getDefenseLevel() >= 5 | super.getAttackLevel() >= 5 ) {
             System.out.println(this.getName() + " seems pretty strong.");
             }
        
             else if (super.getDefenseLevel() <= 4 | super.getAttackLevel() <= 4) {
             System.out.println(this.getName() + " looks like nothing out of the ordinary.");
             }
        }
        
        else if (super.isAlive() == false) {
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
