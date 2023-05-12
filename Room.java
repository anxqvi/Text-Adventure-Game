public class Room {
    
    private Staff staff;
    private Bodyguard bodyguard;
    private Item item;

    public Room(Staff staff, Bodyguard bodyguard, Item item)
    {
        this.staff = staff;
        this.bodyguard = bodyguard;
        this.item = item;
    }
    
    public Staff getStaff() {
        
        return staff;
        
    }
    
    public Bodyguard getBodyguard() {
        
        return bodyguard;
        
    }
    
    public Item getItem() {
        
        return item;
        
    }
    
    public void removeItem() {
        
        item = null;
        
    }
    
    public void printDescription() {
        
        if (staff == null && bodyguard == null && item == null) {
            
            System.out.println("The room is empty.");
            
        }
        
        else {
            System.out.println("In this room...");
            
            if (staff != null) {
                
                staff.printDescription();
                
            }
            
            if (bodyguard != null) {
                
                bodyguard.printDescription();
                
            }
            
            if (item != null) {
                
                item.printDescription();
                
            }
        }
    }
}
