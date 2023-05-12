import java.util.Random;
import java.util.ArrayList;
public class Player extends Character
{
    private int health;
    private int visibility;
    private ArrayList<Item> inventory;
    
    public Player(String name) {
        
        super(name, 5, 5);
        health = 10;
        visibility = 0;
        inventory = new ArrayList<Item>();
        
    }
    
    public int getHealth() {
        
        return health;
        
    }
    
    public int getVisibility() {
        
        return visibility;
        
    }
    
    public ArrayList<Item> getInventory() {
        
        return inventory;
        
    }
    
    public void modifyHealth() {
        
        int change = 0;
        
        for(Item item: getInventory()) {
            
            change += item.getHealthModifier();
            
        }
            
        health += change;
        
    }
    
    public void modifyHealth(int change) {

        health += change;
        
    }
    
    public void modifyVisibility() {
        
        visibility = 0;
        
        int change = 0;
        
        for(Item item: getInventory()) {
            
            change += item.getVisibilityModifier();
            
        }
            
        visibility += change;
        
    }
    
    public void modifyVisiblity(int change) {
        
        visibility += change;
        
    }
    
    public void modifyDefenseLevel() {
        
        super.setDefenseLevel(5);
        
        int change = 0;
        
        for(Item item: getInventory()) {
            
            change += item.getDefenseLevelModifier();
            
        }
        
        super.modifyDefenseLevel(change);
        
    }
    
    public void modifyAttackLevel() {

        int change = 0;
        
        for(Item item: getInventory()) {
            
            change += item.getAttackLevelModifier();
            
        }
        
        super.modifyAttackLevel(change);
        
    }
    
    public boolean escape(boolean stopEscape) {
        
        int escapeChance = 10 + super.getDefenseLevel() - this.getVisibility();
        
        Random random = new Random();
        
        System.out.println("You are attempting to escape to the next room.");
        
        if (stopEscape == true) {
            health -= 1;
            System.out.println("You have failed to escape.");
            return false;
        }
        
        else {
            if ((random.nextInt(escapeChance) + 1) >= 9) {
                System.out.println("You have successfully escaped to the next room.");
                return true;
            }
            else {
                health -= 1;
                System.out.println("You have failed to escape.");
                return false;
            }
        }
    }
    
    public boolean subdue(boolean distracted) {
        
        Random random = new Random();
        
        if (distracted = true) {
            
            if ((random.nextInt(10) + 1) >= 1) {
                return true;
            }
            
            else {
                health -= 1;
                return false;
            }
        }
        
        else {
            if ((random.nextInt(10) + 1) >= 8) {
                return true;
            }
            
            else {
                health -= 1;
                return false;
            }
        }
    }
    
    public void pickup(Item item) {
        inventory.add(item);
    }
    
    public void remove(Item item) {
        inventory.remove(item);
    }
    
    public void clear() {
        inventory.clear();
    }
}
