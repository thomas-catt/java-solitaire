import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game g = new Game();
        try {
            g.init();
        } catch (java.io.FileNotFoundException e) {
            System.out.println(" ! UI template file was not found. Make sure ui-template.txt exists and is writable.\n\n");
            scanner.close();
            return;
        }

        while (g.isPlaying) {
            g.render();

            if (g.hasGameWon()) {
                System.out.println("CONGRATULATIONS! You've won the game.");
                break;
            }
            
            System.out.print("   Enter command:\n > ");
            String cmdString = scanner.nextLine();
            Helper.cls();
    
            try {
                Command c = new Command(cmdString);
                c.execute(g);
                
            } catch (Exception e) {
                System.out.println("Failed to execute command: " + e.getMessage());
                System.out.println("See help by using 'help'.");
            }
        }

        System.out.println("Thank you for playing! :3");
        
        scanner.close();
    }
}