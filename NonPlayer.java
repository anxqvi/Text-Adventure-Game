public class NonPlayer extends Character
{
    private boolean conscious;
    private boolean alive;
    private boolean escaped;
    
    public NonPlayer(String name, int attackLevel, int defenseLevel, boolean conscious, boolean alive, boolean escaped) {
        
        super(name, attackLevel, defenseLevel);
        this.conscious = conscious;
        this.alive = alive;
        this.escaped = escaped;
        
    }
    
    public NonPlayer(String name, int attackLevel, int defenseLevel) {
        
        super(name, attackLevel, defenseLevel);
        conscious = true;
        alive = true;
        escaped = false;
        
    }
    
    public boolean isConscious() {
        
        return conscious;
        
    }
    
    public boolean isAlive() {
        
        return alive;
        
    }
    
    public boolean hasEscaped() {
        
        return escaped;
        
    }
    
    public void knockedOut() {
        
        conscious = false;
        
    }
    
    public void die() {
        
        alive = false;
        
    }
    
    public void escaped() {
        
        escaped = true;
        
    }
}