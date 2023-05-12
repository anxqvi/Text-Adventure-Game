import java.util.Random;
public class Clothes extends Item
{
    private String name;
    
    public Clothes()
    {
        Random random = new Random();
        int num = random.nextInt(10) + 1;
        if (num <=3) {
            name = "kevlar vest";
        }
        else if (num <=7) {
            name = "staff uniform";
        }
        else {
            name = "bodyguard uniform";
        }
    }
    
    public String getName() {
        
        return name;
        
    }
    
     public void stats() {
        
        if (name.equals("kevlar vest")) {
            setVisibilityModifier(5);
            setDefenseLevelModifier(5);
            System.out.println("You stick out heavily but are a lot more secure.");
        }
        
        if (name.equals("staff uniform")) {
            setVisibilityModifier(-2);
            setDefenseLevelModifier(-1);
            System.out.println("You can blend in a little better but are less secure.");
        }
        
        if (name.equals("bodyguard uniform")) {
            setVisibilityModifier(-4);
            setDefenseLevelModifier(2);
            System.out.println("You can blend in a lot better and are a little more secure.");
        }
    }
    
    public void printDescription() {
        if (name.equals("kevlar vest")) {
            System.out.println("A kevlar vest is lying on the floor.");
        }
        
        if (name.equals("staff uniform")) {
            System.out.println("A staff uniform is lying on the floor.");
        }
        
        if (name.equals("bodyguard uniform")) {
            System.out.println("A bodyguard uniform is lying on the floor.");
        }
    }

}
