import java.util.Random;

public class Deck extends Stack<Card> {
    Deck() {
        super();
        for (int i = 1; i < CardType.values().length; i++) {
            CardType type = CardType.values()[i];
            for (int j = 1; j < CardFace.values().length; j++) {
                CardFace face = CardFace.values()[j];
                Card c = new Card(type, face);
                push(c);
            }
        }
    }

    void shuffle() {
        Random rand = new Random();
        for (int i = 0; i < length(); i++) {
            int j = rand.nextInt(length());
            Card temp = this.getIndex(j);
            this.setIndex(j, this.getIndex(i));
            this.setIndex(i, temp);
        }
    }
}
