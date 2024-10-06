import java.io.File;
import java.util.Scanner;

public class Game {
    Stack<Card> stock;
    Stack<Card> waste;
    Stack<Command> history;
    FoundationCards[] foundation;
    ColumnCards[] column;
    boolean isPlaying;
    
    Game() {
        stock = new Stack<>();
        waste = new Stack<>();
        history = new Stack<>();
        isPlaying = true;

        foundation = new FoundationCards[4];
        for (int i = 0; i < 4; i++)
            foundation[i] = new FoundationCards();

        column = new ColumnCards[7];
        for (int i = 0; i < 7; i++)
            column[i] = new ColumnCards(i);
    }

    void init() throws Exception {
        Deck gameStack = new Deck();
        gameStack.shuffle();

        for (int i = 0; i < 7; i++)
            for (int j = 0; j <= i; j++)
                column[i].pushForce(gameStack.pop());

        while (gameStack.length() > 0)
            stock.push(gameStack.pop());

        // read UI template from file
        File uiTemplate = new File("ui-template.txt");
        Scanner uiScanner = new Scanner(uiTemplate);
        while (uiScanner.hasNextLine()) {
            UITemplateData.ui += uiScanner.nextLine() + "\n";
        }
        uiScanner.close();
    }

    boolean hasGameWon() {
        for (int i = 0; i < 4; i++) {
            if (foundation[i].length() < 13)
                return false;
        }

        isPlaying = false;
        return true;
    }

    void render() {
        String ui = UITemplateData.ui;

        // set stock and waste sizes
        ui = Helper.writeXY(ui, UITemplateData.stockSizeLocation, Helper.setw(stock.length(), 2));
        ui = Helper.writeXY(ui, UITemplateData.wasteSizeLocation, Helper.setw(waste.length(), 2));
        
        // set stock and waste stack topmost cards
        String stockTopCard = "";
        if (stock.length() > 0)
            stockTopCard = "[ ]";
        ui = Helper.writeXY(ui, UITemplateData.stockCardLocation, Helper.setw(stockTopCard, 3));
        
        String wasteTopCard = "";
        if (waste.length() > 0)
            wasteTopCard = waste.top().toString();
        ui = Helper.writeXY(ui, UITemplateData.wasteCardLocation, Helper.setw(wasteTopCard, 3));
        
        // set foundation cards and sizes
        for (int i = 0; i < 4; i++) {
            // set the card string to either be foundation stack's topmost card string, or an empty string if thats not available
            String foundationTopCard = foundation[i].top() != null ? foundation[i].top().toString() : "";
            
            // to calculate the horizontal position (or x, or 0 index of position array), i do startPos + offset
            // where offset is calculated by foundation index times the foundationMultiple, which is the equal displacement between each target location
            int[] targetPos = UITemplateData.foundationCardStartLocation.clone();
            targetPos[0] += i * UITemplateData.foundationMultiple;
            ui = Helper.writeXY(ui, targetPos, Helper.setw(foundationTopCard, 3));

            // same horizontal position calculating logic goes for the foundation sizes positions
            int foundationSize = foundation[i].length();
            targetPos = UITemplateData.foundationSizeStartLocation.clone();
            targetPos[0] += i * UITemplateData.foundationMultiple;
            ui = Helper.writeXY(ui, targetPos, Helper.setw(foundationSize, 2));
        }    

        // set column cards and sizes
        for (int i = 0; i < 7; i++) {
            int[] targetPos;
            for (int j = 0; j < column[i].length(); j++) {                
                String columnCard = column[i].getCardAt(j) != null ? column[i].getCardAt(j).toString() : "";
    
                targetPos = UITemplateData.columnCardStartLocation.clone();
                targetPos[0] += i * UITemplateData.columnMultiple;
                // adding j into the y-coordinate is responsible for vertical displacement, as cards are arranged vertically in the UI
                targetPos[1] += j;
                ui = Helper.writeXY(ui, targetPos, Helper.setw(columnCard, 3));
            }

            int columnSize = column[i].length();
            targetPos = UITemplateData.columnSizeStartLocation.clone();
            targetPos[0] += i * UITemplateData.columnMultiple;
            ui = Helper.writeXY(ui, targetPos, Helper.setw(columnSize, 2));
        }
        
        Helper.printCardColoredString(ui);
    }
}
