import java.util.Random;
public class Target extends NonPlayer
{
    private int distanceFromWeapon;
    
    public Target(String name)
    {
        super(name, 7, 7, true, true, false);
        Random random = new Random();
        
        distanceFromWeapon = random.nextInt(8) + 1;
    }
    
    public int getDistanceFromWeapon() {
        
        return distanceFromWeapon;
        
    }
    
    public boolean grabWeapon(Character c) {
        
        if (distanceFromWeapon > c.getDefenseLevel()) {
            return true;
        }
        
        else {
            return false;
        }
        
    }
    
    public int attack(Character c) {
        
        int result = 0;
        
        Random random = new Random();
        
        int statcheck = this.getAttackLevel() - c.getDefenseLevel();
        
        if (grabWeapon(c) == true) {
            
            result = statcheck + (random.nextInt(4) - 1);
        }
        else {
            result = statcheck + (random.nextInt(4) - 2);
        }
        
        if (result > 0) {
            return 1;
        }
        else if (result < 0) {
            return -1;
        }
        else {
            return 0;
        }
    }
    
    public void printDescription() {
        
        if (super.isAlive() == true && super.isConscious() == true) {
            
            System.out.println(this.getName() + " appears to be walking back and forth anxiously.");
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
        
        else {
            System.out.println(this.getName() + " is knocked out.");
        }
        
    }

}
