import java.util.Scanner;

public class Command {
    CommandAction action;
    String from;
    String to;
    int amount;
    
    Command(String input) throws Exception {
        String[] arguments = input.split(" ");
        String action = "";
        this.amount = 1;
        
        if (arguments.length > 0)
            action = arguments[0];
        else
            action = input;

        switch (action) {
            case "m":
                this.action = CommandAction.MOVE;
                if (arguments.length < 3)
                    throw new Exception("Incomplete arguments for command 'm'.");

                from = arguments[1];
                to = arguments[2];
                if (arguments.length >= 4) {
                    try {
                        this.amount = Integer.parseInt(arguments[3]);
                    } catch (Exception e) {
                        throw new Exception("Card move amount must be a number, \"" + arguments[3] + "\" received.");
                    }
                }
                
                break;
        
            case "s":
                this.action = CommandAction.DRAW_STOCK;
                break;
        
            case "e":
                this.action = CommandAction.EXIT;
                break;
        
            case "z":
                this.action = CommandAction.UNDO;
                break;
            
            case "help":
                this.action = CommandAction.HELP;
                break;
        
            default:
                throw new Exception("Command \"" + action + "\" not found");
        }
    }

    void execute(Game game) throws Exception {
        switch (this.action) {
            case CommandAction.DRAW_STOCK:
                if (game.stock.length() > 0)
                    game.waste.push(game.stock.pop());
                else
                    while (game.waste.length() > 0)
                        game.stock.push(game.waste.pop());

                break;

            case CommandAction.MOVE:
                if (to.equals("w"))
                    throw new Exception("Can't move to waste stack.");

                char fromDeck = from.charAt(0);
                int fromIndex = -1;
                char toDeck = to.charAt(0);
                int toIndex = -1;
                    
                String validFromDecks = "cfw";
                String validToDecks = "cf";

                if (!validFromDecks.contains(Character.toString(fromDeck)))
                    throw new Exception("Invalid source deck.");

                if (!validToDecks.contains(Character.toString(toDeck)))
                    throw new Exception("Invalid destination deck.");

                if (to.length() < 2)
                    throw new Exception("No source deck stack number provided (e.g. c1, f4, etc.)");

                if (fromDeck != 'w' && from.length() < 2)
                    throw new Exception("No destination deck stack number provided (e.g. c1, f4, etc.)");

                if (fromDeck != 'w') {
                    try {
                        fromIndex = Integer.parseInt(from.substring(1)) - 1;
                    } catch (Exception e) {
                        throw new Exception("Source stack index must be a number, \"" + from.substring(1) + "\" received.");
                    }

                    if (fromDeck == 'c' && fromIndex > 6)
                        throw new Exception("Column " + from.substring(1) + " doesn't exist.");
                    if (fromDeck == 'f' && fromIndex > 3)
                        throw new Exception("Foundation " + from.substring(1) + " doesn't exist.");
                }

                try {
                    toIndex = Integer.parseInt(to.substring(1)) - 1;
                } catch (Exception e) {
                    throw new Exception("Destination stack index must be a number, \"" + to.substring(1) + "\" received.");
                }
            
                if (toDeck == 'c' && toIndex > 6)
                    throw new Exception("Column " + to.substring(1) + " doesn't exist.");
                if (toDeck == 'f' && toIndex > 3)
                    throw new Exception("Foundation " + to.substring(1) + " doesn't exist.");

                Stack<Card> source = null;
                Stack<Card> destination = null;
                
                if (fromDeck == 'c') source = game.column[fromIndex];
                else if (fromDeck == 'f') source = game.foundation[fromIndex];
                else if (fromDeck == 'w') source = game.waste;
                
                if (toDeck == 'c') destination = game.column[toIndex];
                else if (toDeck == 'f') destination = game.foundation[toIndex];

                Stack<Card> temp = new Stack<>();
                for (int i = 0; i < amount; i++)
                    temp.push(source.pop());

                Stack<Card> pushedCards = new Stack<>();
                try {
                    while (temp.length() > 0) {
                        pushedCards.push(temp.pop());
                        destination.push(pushedCards.top());                
                    }
                    
                } catch (Exception e) {
                    for (int i = 0; i < pushedCards.length() - 1; i++) {
                        destination.pop();
                    }
                    
                    while (pushedCards.length() > 0)
                        temp.push(pushedCards.pop());

                    while (temp.length() > 0)
                        source.pushForce(temp.pop());  

                    throw new Exception("Can't move to this deck: " + e.getMessage());
                }


                
                break;
                    
            case CommandAction.EXIT:
                game.isPlaying = false;
                break;

            case CommandAction.HELP:
                System.out.println("commands:\n"+
                    " > m [source] [destination] [?amount]: move [amount] number of cards from [source] to [destination]\n" +
                    "   [source]: waste pile 'w', any column 'c1' to 'c7', any foundation stack 'f1' to 'f4'\n" + 
                    "   [destination]: any column 'c1' to 'c7', any foundation stack 'f1' to 'f4'\n" +
                    "   [amount] optional: the number of cards to move (default is 1 card)\n" +
                    " > s: draw a card from stock pile to waste pile\n" + 
                    " > z: undo\n" + 
                    " > e: exit game\n"
                );
                break;
        
            default:
                break;
        }
    }
}
