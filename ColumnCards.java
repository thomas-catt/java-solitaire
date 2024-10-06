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
}
