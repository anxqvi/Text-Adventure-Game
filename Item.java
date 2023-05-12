public class Item
{
    private int visibility_modifier;
    private int attackLevel_modifier;
    private int defenseLevel_modifier;
    private int health_modifier;

    public Item(int visibility, int attackLevel, int defenseLevel, int health) {
  
        visibility_modifier = visibility;
        attackLevel_modifier = attackLevel;
        defenseLevel_modifier = defenseLevel;
        health_modifier = health;
        
    }
    
    public Item() {
  
        visibility_modifier = 0;
        attackLevel_modifier = 0;
        defenseLevel_modifier = 0;
        health_modifier = 0;
        
    }
    
    public int getVisibilityModifier() {
        
        return visibility_modifier;
        
    }
    
    public int getAttackLevelModifier() {
        
        return attackLevel_modifier;
        
    }
    
    public int getDefenseLevelModifier() {
        
        return defenseLevel_modifier;
        
    }
    
    public int getHealthModifier() {
        
        return health_modifier;
        
    }
    
    public void setVisibilityModifier(int change) {
        
        visibility_modifier = change;
        
    }
    
    public void setAttackLevelModifier(int change) {
        
        attackLevel_modifier = change;
        
    }
    
    public void setDefenseLevelModifier(int change) {
        
        defenseLevel_modifier = change;
        
    }
    
    public void setHealthModifier(int change) {
        
        health_modifier = change;
        
    }
    
    public void stats() {
        
        setHealthModifier(0);
        setVisibilityModifier(0);
        setDefenseLevelModifier(0);
        setAttackLevelModifier(0);
        
    }
    
    public void printDescription() {
        
        System.out.println("An item is lying  on the floor");
        
    }
}
