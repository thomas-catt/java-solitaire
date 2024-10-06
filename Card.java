public class Card {
    CardType type;
    CardFace face;
    CardColor color;

    Card() {
        type = CardType.HIDDEN;
        face = CardFace.HIDDEN;
        color = CardColor.HIDDEN;
    }

    Card(CardType type, CardFace face) {
        this.type = type;
        this.face = face;

        if (type == CardType.DIAMONDS || type == CardType.HEARTS)
            this.color = CardColor.RED;

        else
            this.color = CardColor.BLACK;
    }

    @Override
    public String toString() {
        String s = "";

        int cardTypeIndex;
        for (cardTypeIndex = 0; CardType.values()[cardTypeIndex] != type; cardTypeIndex++);
        s += CARD_NAMES.CARD_TYPE_STRING[cardTypeIndex];

        int cardFaceIndex;
        for (cardFaceIndex = 0; CardFace.values()[cardFaceIndex] != face; cardFaceIndex++);
        if (CARD_NAMES.CARD_FACE_STRING[cardFaceIndex].length() == 1)
            s += " ";
        s += CARD_NAMES.CARD_FACE_STRING[cardFaceIndex];

        return s;
    }
}
