public class ColumnCards extends Stack<Card> {
    int hiddenCards;
    
    ColumnCards(int hiddenCards) {
        super();
        this.hiddenCards = hiddenCards;
    }

    Card getCardAt(int index) {
        if (index >= this.length())
            return null;

        if (index < hiddenCards)
            return new Card(CardType.HIDDEN, CardFace.HIDDEN);

        return getIndex(index);
    }

    @Override
    Card pop() {
        Card toreturn = super.pop();
        if (length() <= this.hiddenCards)
            this.hiddenCards = length() - 1;

        return toreturn;
    }

    @Override
    boolean push(Card value) throws Exception {
        Card top = top();

        if (top == null) {
            if (value.face != CardFace.KING)
                throw new Exception("Column stack can only start with the King card.");

            return super.push(value);
        }
        
        int faceIndex;
        for (faceIndex = 0; top.face != CardFace.values()[faceIndex]; faceIndex++);

        if (value.face != CardFace.values()[faceIndex - 1])
            throw new Exception("Card to add must be exactly one rank lower than the current topmost card on the column stack.");
            
        if (value.color == top.color)
            throw new Exception("Card to add must have opposite color to the current topmost card on the column stack.");
        
        return super.push(value);
    }

    @Override
    boolean pushForce(Card value) throws Exception {
        return super.push(value);
    }
}
