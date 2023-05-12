import java.util.Random;
public class Character
{
    private String name;
    private int attackLevel;
    private int defenseLevel;

    public Character(String name, int attackLevel, int defenseLevel) {
        
        this.name = name;
        this.attackLevel = attackLevel;
        this.defenseLevel = defenseLevel;
        
    }
    
    public String getName() {
        
        return name;
    }
    
    public int getAttackLevel() {
        
        return attackLevel;
    }
    
    public int getDefenseLevel() {
        
        return defenseLevel;
        
    }
    
    public void setName(String name) {
        
        this.name = name;
        
    }
    
    public void setAttackLevel(int change) {
        
        attackLevel = change;
        
    }
    
    public void setDefenseLevel(int change) {
        
        defenseLevel = change;
        
    }
    
    public void modifyAttackLevel(int change) {
        
        attackLevel += change;
        
    }
    
    public void modifyDefenseLevel(int change) {
        
        defenseLevel += change;
        
    }
    
    public boolean escape(Character c, boolean stopEscape) {
        
        int escapeChance = 10 + c.getDefenseLevel();
        int escapeAttempt = 0;
        
        Random random = new Random();
        
        System.out.println(c.getName() + " is attempting to escape.");
        if (stopEscape == true) {
            System.out.println(c.getName() + " has failed to escape.");
            return false;
        }
        else {
            escapeAttempt = random.nextInt(escapeChance) + 1;
            if (escapeAttempt >= 9) {
                
                System.out.println(c.getName() + " has successfully escaped.");
                return true;
            }
            
            else {
                System.out.println(c.getName() + " has failed to escape.");
                return false;
            }
        }
    }
    
    public boolean stopEscape(Character c) {
        
        int stopChance = 10 + this.getAttackLevel();
        
        Random random = new Random();
        
        if ((random.nextInt(stopChance) + 1) >= c.getDefenseLevel()) {
            
            return true;
        }
        else {
            return false;
        }
    }
    
    public int attack(Character c) {
        
        Random random = new Random();
        
        int statcheck = this.getAttackLevel() - c.getDefenseLevel();
        
        int result = statcheck + (random.nextInt(4) - 2);
        
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
        
        System.out.println(this.getName() + " appears to be in the room.");
        
        if (defenseLevel >= 6 | attackLevel >= 6 ) {
            System.out.println(this.getName() + " seems pretty strong.");
        }
        
        else if (defenseLevel <= 5 | attackLevel <= 5) {
            System.out.println(this.getName() + " looks like nothing out of the ordinary.");
        }
        
    }
}
