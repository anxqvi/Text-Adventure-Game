import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class Game {
    
    public static void main(String[] args) {
        boolean validInput = true;
        String[] options = new String[10];
        String userInput = "";
        int roomNumber = 0;
        Room[] rooms = new Room[4];
        Scanner keyboard = new Scanner(System.in);
        String[] names = {"Robert", "Henry", "Zack", "Nick", "Sarah", "Laura", "Rachel", "Nicole"};
        int nameIndex = 0;
        
        for (int i = 0; i < 4; i++) {
            rooms[i] = createRoom();
        }
        
        for (int i = 0; i < 4; i++) {
            if (rooms[i].getStaff() != null) {
                rooms[i].getStaff().setName(names[nameIndex]);
                nameIndex++;
            }
            if (rooms[i].getBodyguard() != null) {
                rooms[i].getBodyguard().setName(names[nameIndex]);
                nameIndex++;
            }
        }
            
        
        Player player = createPlayer();
        Target target = createTarget();
        
        System.out.println("Welcome to the Level Two adventure game " + player.getName() + "!");
        System.out.println();
        
        System.out.println("Your objective is to traverse through these rooms however you see fit and kill the target.");
        System.out.println("The target's name is " + target.getName() + " and he will be located in the last room alone.");
        System.out.println("Good luck agent.");
        System.out.println();
        
        while (player.getHealth() > 0 && target.isAlive() == true) {
            
            System.out.println("Health: " + player.getHealth() + " | Visibility: " + player.getVisibility() + " | Attack: " + player.getAttackLevel() + " | Defense: " + player.getDefenseLevel());
            System.out.println();
            
            if (roomNumber == 4) {
                
                System.out.println("You have made it to the final room, finish the job.");
                
                target.printDescription();
                System.out.println();
                
                while (player.getHealth() > 0 && target.isAlive() == true) {
                    System.out.println("(at) Attack the target");
                
                    userInput = keyboard.nextLine();
                
                    if (!userInput.equals("at")) {
                        while (!userInput.equals("at")) {
                             System.out.println("Your input was not valid.");
                             System.out.println("Please try again: ");
                             userInput = keyboard.nextLine();
                        }
                    }
                    System.out.println(target.getName() + " tries to attack you before you can attack him.");
                    int resultTarget = target.attack(player);
                    System.out.println();
                    
                    if (resultTarget == 1) {
                        System.out.println(target.getName() + " was successful and damaged you a little.");
                        player.modifyHealth(-2);
                    }
                    else if (resultTarget == -1) {
                        System.out.println(target.getName() + " was unsuccessful and you manage to kill him instead.");
                        target.die();
                        break;
                    }
                
                    else {
                        System.out.println("His attack resulted in a stalemate.");
                    }
                    
                    if (player.getHealth() <= 0) {
                        break;
                    }
                    System.out.println();
                    System.out.println(target.getName() + " leaves himself open to a counter...");
                    System.out.println();
                    System.out.println("(at) Attack the target");
                    
                    userInput = keyboard.nextLine();
                
                    if (!userInput.equals("at")) {
                        while (!userInput.equals("at")) {
                             System.out.println("Your input was not valid.");
                             System.out.println("Please try again: ");
                             userInput = keyboard.nextLine();
                        }
                    }
                    int resultPlayer = player.attack(target);
                    if (resultTarget == 1) {
                        System.out.println("You have killed " + target.getName() + ".");
                        target.die();
                        break;
                    }
                    else if (resultTarget == -1) {
                        System.out.println(target.getName() + " managed to react in time and hit you.");
                        player.modifyHealth(-2);
                    }
                
                    else {
                        System.out.println("His attack resulted in a stalemate.");
                        player.modifyHealth(-2);
                    }
                }
                
                break;
            }
            
            rooms[roomNumber].printDescription();
            System.out.println();
            
            Arrays.fill(options, null);
            options = printRoomOptions(rooms[roomNumber], roomNumber);
            System.out.println();
            
            System.out.println("Please enter a valid option:");
            
            userInput = keyboard.nextLine();
            
            for (int i = 0; i < 10; i++) {
                
                if (userInput.equals(options[i])) {
                    validInput = true;
                    break;
                }
            }
            
            if (validInput = false) {
                
                while(validInput = false) {
                    
                    System.out.println("Your input was invalid.");
                    System.out.println("Please try again:");
                    userInput = keyboard.nextLine();
                    
                    for (int i = 0; i < 6; i++) {
                        
                        if (userInput.equals(options[i])) {
                            validInput = true;
                            break;
                        }
                    }
                }
            }
            
            if (userInput.equals("e")) {
                
                boolean stopEscape = false;
                boolean escapeSuccess;
                
                if (rooms[roomNumber].getBodyguard() != null && rooms[roomNumber].getBodyguard().isAlive() == true && rooms[roomNumber].getBodyguard().isConscious() == true && 
                rooms[roomNumber].getBodyguard().hasEscaped() == false) {
                    
                    stopEscape = rooms[roomNumber].getBodyguard().stopEscape(player);
                    
                }
                
                escapeSuccess = player.escape(stopEscape);
                
                if (escapeSuccess == true) {
                    roomNumber++;
                    continue;
                }
                
                else {
                    continue;
                }
                
            }
            
            if (userInput.equals("m")) {
                
                System.out.println("You move to the next room.");
                roomNumber++;
                continue;
                
            }
            
            if (userInput.equals("as")) {
                
                boolean escapeSuccess = false;
                boolean stopEscape = false;
                
                System.out.println("You are attacking " + rooms[roomNumber].getStaff().getName() + ".");
                
                int result = player.attack(rooms[roomNumber].getStaff());
                
                if (result == 1) {
                    System.out.println("You have defeated " + rooms[roomNumber].getStaff().getName() + ".");
                    rooms[roomNumber].getStaff().die();
                    continue;
                }
                else if (result == -1) {
                    System.out.println("You were defeated by " + rooms[roomNumber].getStaff().getName() + ".");
                    player.modifyHealth(-1);
                    escapeSuccess = rooms[roomNumber].getStaff().escape(player, stopEscape);
                    if (escapeSuccess == true) {
                        rooms[roomNumber].getStaff().escaped();
                    }
                    continue;
                }
                
                else {
                    System.out.println("The fight ended in a stalemate.");
                    stopEscape = player.stopEscape(rooms[roomNumber].getStaff());
                    escapeSuccess = rooms[roomNumber].getStaff().escape(player, stopEscape);
                    if (escapeSuccess == true) {
                        rooms[roomNumber].getStaff().escaped();
                    }
                    continue;
                }
            }
            
            if (userInput.equals("ss")) {
                
                boolean escapeSuccess = false;
                boolean stopEscape = false;
                
                System.out.println("You are attempting to subdue " + rooms[roomNumber].getStaff().getName() + ".");
                
                boolean result = player.subdue(false);
                
                if (result == true) {
                    System.out.println("You have subdued " + rooms[roomNumber].getStaff().getName() + ".");
                    rooms[roomNumber].getStaff().knockedOut();
                    continue;
                }
                else {
                    System.out.println("You have failed to subdue " + rooms[roomNumber].getStaff().getName() + ".");
                    player.modifyHealth(-1);
                    escapeSuccess = rooms[roomNumber].getStaff().escape(player, stopEscape);
                    if (escapeSuccess == true) {
                        rooms[roomNumber].getStaff().escaped();
                    }
                    continue;
                }
            }
            
            if (userInput.equals("ab")) {
                
                boolean escapeSuccess = false;
                boolean stopEscape = false;
                
                System.out.println("You are attacking " + rooms[roomNumber].getBodyguard().getName() + ".");
                
                int result = player.attack(rooms[roomNumber].getBodyguard());
                
                if (result == 1) {
                    System.out.println("You have defeated " + rooms[roomNumber].getBodyguard().getName() + ".");
                    rooms[roomNumber].getBodyguard().die();
                    continue;
                }
                else if (result == -1) {
                    System.out.println("You were defeated by " + rooms[roomNumber].getBodyguard().getName() + ".");
                    player.modifyHealth(-2);
                    continue;
                }
                
                else {
                    System.out.println("The fight ended in a stalemate.");
                    player.modifyHealth(-1);
                    continue;
                }
                
            }
            
            if (userInput.equals("sb")) {
                
                boolean escapeSuccess = false;
                boolean stopEscape = false;
                
                System.out.println("You are attempting to subdue " + rooms[roomNumber].getBodyguard().getName() + ".");
                
                boolean result = player.subdue(rooms[roomNumber].getBodyguard().isDistracted());
                
                if (result == true) {
                    System.out.println("You have subdued " + rooms[roomNumber].getBodyguard().getName() + ".");
                    rooms[roomNumber].getBodyguard().knockedOut();
                    continue;
                }
                else {
                    System.out.println("You have failed to subdue " + rooms[roomNumber].getBodyguard().getName() + ".");
                    player.modifyHealth(-2);
                    continue;
                }
                
            }
            
            if (userInput.equals("p")) {
                
                if (rooms[roomNumber].getItem() instanceof Coin) {
                    player.pickup(rooms[roomNumber].getItem());
                    player.modifyVisibility();
                    Coin temp = (Coin) rooms[roomNumber].getItem();
                    String[] optionsCoin = new String[2];
                    int i = 0;
                    
                    if (rooms[roomNumber].getBodyguard() != null && rooms[roomNumber].getBodyguard().isAlive() == true && rooms[roomNumber].getBodyguard().isConscious() == true && 
                    rooms[roomNumber].getBodyguard().hasEscaped() == false) {
                        System.out.println("(db) Distract " + rooms[roomNumber].getBodyguard().getName());
                        optionsCoin[i] = "db";
                        i++;
                    }
                    
                    if (rooms[roomNumber].getStaff() != null && rooms[roomNumber].getStaff().isAlive() == true && rooms[roomNumber].getStaff().isConscious() == true && 
                    rooms[roomNumber].getStaff().hasEscaped() == false) {
                        System.out.println("(ds) Distract " + rooms[roomNumber].getStaff().getName());
                        optionsCoin[i] = "ds";
                    }
                    
                    if ((rooms[roomNumber].getBodyguard() == null || rooms[roomNumber].getBodyguard().isAlive() == false || rooms[roomNumber].getBodyguard().isConscious() == false || 
                    rooms[roomNumber].getBodyguard().hasEscaped() == true) && (rooms[roomNumber].getStaff() == null || rooms[roomNumber].getStaff().isAlive() == false || 
                    rooms[roomNumber].getStaff().isConscious() == false || rooms[roomNumber].getStaff().hasEscaped() == true)) {
                        System.out.println("There is no one here to distract.");
                        player.remove(rooms[roomNumber].getItem());
                        rooms[roomNumber].removeItem();
                        continue;
                    }
                    
                    System.out.println("Who are you attempting to distract?");
                    
                    userInput = keyboard.nextLine();
                    
                    for (int j = 0; j < 2; j++) {
                        if (userInput.equals(optionsCoin[j])) {
                            validInput = true;
                            break;
                        }
                    }
                    
                    if (validInput = false) {
                        while (validInput = false) {
                            System.out.println("Your input was invalid.");
                            System.out.println("Please try again: ");
                            
                            userInput = keyboard.nextLine();
                            
                            for (int j = 0; j < 2; j++) {
                                if (userInput.equals(optionsCoin[j])) {
                                    validInput = true;
                                    break;
                                }
                            }
                        }
                    }
                    
                    if (userInput.equals("db")) {
                        System.out.println("You toss the coin in attempt to distract " + rooms[roomNumber].getBodyguard().getName());
                        boolean distractSuccess = temp.distract(rooms[roomNumber].getBodyguard().isDistracted());
                        if (distractSuccess = true) {
                            System.out.println(rooms[roomNumber].getBodyguard().getName() + " is distracted.");
                            rooms[roomNumber].getBodyguard().distracted();
                        }
                        else {
                            System.out.println(rooms[roomNumber].getBodyguard().getName() + " does not notice anything.");
                        }
                    }
                    
                    else {
                        System.out.println("You toss the coin in attempt to distract " + rooms[roomNumber].getStaff().getName());
                        System.out.println(rooms[roomNumber].getStaff().getName() + " does not notice anything.");
                    }
                    
                    player.remove(rooms[roomNumber].getItem());
                    rooms[roomNumber].removeItem();
                    continue;
                
                }
                
                else if (rooms[roomNumber].getItem() instanceof Food) {
                    player.pickup(rooms[roomNumber].getItem());
                    Food temp = (Food) rooms[roomNumber].getItem();
                    
                    System.out.println("You eat the " + temp.getName() + ".");
                    
                    rooms[roomNumber].getItem().stats();
                    
                    if  (temp.getName().equals("large pizza")) {
                        player.modifyHealth();
                        player.modifyAttackLevel();
                    }
                    
                    else if (temp.getName().equals("burger")) {
                        player.modifyHealth();
                        player.modifyAttackLevel();
                    }
                    else {
                        player.modifyHealth();
                        player.modifyAttackLevel();
                    }
                    player.remove(rooms[roomNumber].getItem());
                    rooms[roomNumber].removeItem();
                    continue;
                }
                
                else {
                    Clothes temp = (Clothes) rooms[roomNumber].getItem();
                    
                    player.clear();
                    
                    player.pickup(rooms[roomNumber].getItem());
                    
                    System.out.println("You put on the " + temp.getName() + ".");
                    
                    rooms[roomNumber].getItem().stats();
                    
                    if  (temp.getName().equals("kevlar vest")) {
                        player.modifyVisibility();
                        player.modifyDefenseLevel();
                    }
                    
                    else if (temp.getName().equals("staff uniform")) {
                        player.modifyVisibility();
                        player.modifyDefenseLevel();
                    }
                    else {
                        player.modifyVisibility();
                        player.modifyDefenseLevel();
                    }
                    rooms[roomNumber].removeItem();
                    continue;
                }
            }
        }
        
        if (target.isAlive() == false) {
            System.out.println("Congratulations on a successful mission agent.");
        }
        else {
            System.out.println("Failure is unacceptable, report back to headquarters immediately.");
        }
        
    }
    
    // Creates the player
    public static Player createPlayer() {
        
        Player p;
        String name;
        
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter your name:");
        name = keyboard.nextLine();
        p = new Player(name);
        
        return p;
    }
    
    // Creates a staff NPC based on chance
    public static Staff createStaff() {
        
        Random random = new Random();
        if((random.nextInt(10) + 1) > 6) {
            return null;
        }
        else {
            Staff staff = new Staff("name");
            return staff;
        }
    }
    
    // Creates a bodyguard NPC based on chance
    public static Bodyguard createBodyguard() {
        
        Random random = new Random();
        if((random.nextInt(10) + 1) > 6) {
            return null;
        }
        else {
            Bodyguard bodyguard = new Bodyguard("name");
            return bodyguard;
        }
    }
    
    // Creates an item of a random subtype
    public static Item createItem() {
        
        Item item;
        Random random = new Random();
        int num = random.nextInt(10) + 1;
        if (num <= 2) {
            item = new Coin();
        }
        else if (num <= 6) {
            item = new Clothes();
        }
        else {
            item = new Food();
        }
        return item;
        
    }
    
    // Creates a target
    public static Target createTarget() {
        Target target = new Target("Makarov");
        return target;
    }
    
    // Creates a room
    public static Room createRoom() {
        Room room = new Room(createStaff(), createBodyguard(), createItem());
        return room;
    }
    
    // Prints out the room options and returns an array of valid options to choose from
    public static String[] printRoomOptions(Room room, int roomNumber) {
        String[] options = new String[10];
        int i = 0;
        
        if ((room.getBodyguard() != null && room.getBodyguard().isAlive() == true && room.getBodyguard().isConscious() == true && room.getBodyguard().hasEscaped() == false) || 
        (room.getStaff() != null  && room.getStaff().isAlive() == true && room.getStaff().isConscious() == true && room.getStaff().hasEscaped() == false)) {
                System.out.println("(e) You can attempt to escape to the next room.");
                options[i] = "e";
                i++;
        }
            
        else {
                System.out.println("(m) You can move to the next room.");
                options[i] = "m";
                i++;
        }
        
        if (room.getStaff() != null  && room.getStaff().isAlive() == true && room.getStaff().isConscious() == true && room.getStaff().hasEscaped() == false) {
                System.out.println("(as) You can attempt to fight " + room.getStaff().getName() + ".");
                System.out.println("(ss) You can attempt to subdue " + room.getStaff().getName() + ".");
                options[i] = "as";
                i++;
                options[i] = "ss";
                i++;
        }
            
        if (room.getBodyguard() != null && room.getBodyguard().isAlive() == true && room.getBodyguard().isConscious() == true && room.getBodyguard().hasEscaped() == false) {
                System.out.println("(ab) You can attempt to fight " + room.getBodyguard().getName() + ".");
                System.out.println("(sb) You can attempt to subdue " + room.getBodyguard().getName() + ".");
                options[i] = "ab";
                i++;
                options[i] = "sb";
                i++;
        }
            
        if (room.getItem() != null) {
                System.out.println("(p) You can pick up and use the item.");
                options[i] = "p";
        }
        
        return options;
            
        }
    }
