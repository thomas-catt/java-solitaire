import java.util.Scanner;

public class Main {
    public static void main_(String[] args) throws Exception {
        Stack<Integer> s = new Stack<>();
        s.print();
        s.push(1);
        s.push(2);
        s.push(3);
        s.print();
        System.out.println("Read 0: " + s.getIndex(0));
        System.out.println("Read 1: " + s.getIndex(1));
        System.out.println("Read 2: " + s.getIndex(2));
    }
    
    public static void main(String[] args) throws Exception {
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
                
                if (c.execute(g))
                    g.history.push(c);
                
            } catch (Exception e) {
                System.out.println("Failed to execute command: " + e.getMessage());
                e.printStackTrace();
                System.out.println("See help by using 'help'.");
            }
        }

        System.out.println("Thank you for playing! :3");
        
        scanner.close();
    }
}