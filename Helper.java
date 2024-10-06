public class Helper {
    static void cls() {
        try {
            System.out.print("\033[H\033[2J");  
            System.out.flush();            
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();   
        } catch (Exception e) {}
    }

    static String setw(String s, int w) {
        String newS = s;
        for (int i = s.length(); i < w; i++) {
            newS += " ";
        }

        return newS;
    }

    static String setw(int n, int w) {
        String s = "" + n;
        String newS = s;
        for (int i = s.length(); i < w; i++) {
            newS = "0" + newS;
        }

        return newS;
    }

    static String insertStringAt(String s,  int index, String c) {
        return s.substring(0, index) + c + s.substring(index);
    }

    static String writeStringAt(String s, int index, String c) {
        return s.substring(0, index) + c + s.substring(index + c.length());
    }

    static String writeXY(String destination, int[] coords, String source) {
        int x = coords[0];
        int y = coords[1];
        
        String[] destLines = destination.split("\n");

        destLines[y] = Helper.writeStringAt(destLines[y], x, source);
        
        destination = "";
        for (int i = 0; i < destLines.length; i++)
            destination += destLines[i] + "\n";
        
        return destination;
    }

    static void printCardColoredString(String s) {
        final String RESET = "\u001B[0m\u001B[40m";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            for (int j = 1; j < CARD_NAMES.CARD_TYPE_STRING.length; j++) {
                if (CARD_NAMES.CARD_TYPE_STRING[j] == c) {
                    Card card = new Card(CardType.values()[j], CardFace.ACE);

                    int cardColorIndex;
                    for (cardColorIndex = 0; CardColor.values()[cardColorIndex] != card.color; cardColorIndex++);
                    String colorString = CARD_NAMES.CARD_COLOR_STRING[cardColorIndex];

                    if (colorString.length() > 0) {
                        s = Helper.insertStringAt(s, i + 3, RESET);
                        s = Helper.insertStringAt(s, i, colorString);
                        i += colorString.length() + RESET.length();
                    }
                }
            }
        }

        System.out.println(s);
    }
}
