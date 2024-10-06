enum CardType {
    HIDDEN, CLUBS, DIAMONDS, HEARTS, SPADES
}

enum CardColor {
    HIDDEN, RED, BLACK
}

enum CardFace {
    HIDDEN, ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
}

class CARD_NAMES {
    static final char[] CARD_TYPE_STRING = {'[', (char)(5), (char)(4), (char)(3), (char)(6)};
    static final String[] CARD_COLOR_STRING = {" ", "\u001B[47m\u001B[31m", "\u001B[47m\u001B[30m"};
    static final String[] CARD_FACE_STRING = {"]", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
}

enum CommandAction {
    MOVE, DRAW_STOCK, DRAW_STOCK_REVERSE, UNDO, EXIT, HELP
}

class UITemplateData {
    static String ui = "";
    
    static final int[] stockSizeLocation = {22, 4};
    static final int[] stockCardLocation = {28, 3};

    static final int[] wasteSizeLocation = {67, 4};
    static final int[] wasteCardLocation = {73, 3};

    static final int[] foundationCardStartLocation = {8, 9};
    static final int[] foundationSizeStartLocation = {12, 9};
    static final int   foundationMultiple  = 23;

    static final int[] columnCardStartLocation = {8, 16};
    static final int[] columnSizeStartLocation = {6, 14};
    static final int   columnMultiple  = 13;
}