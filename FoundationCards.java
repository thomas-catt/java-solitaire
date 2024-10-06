public class FoundationCards extends Stack<Card> {
    @Override
    boolean push(Card value) throws Exception {
        Card top = top();

        if (value.face != CardFace.values()[length() + 1])
            throw new Exception("Foundation stack can only hold cards in ascending rank starting from A, without skipping any rank.");
            
        if (top != null && value.type != top.type)
            throw new Exception("Card to add must be of the same type as the current topmost card on the foundation stack.");
        
        return super.push(value);
    }
    
    @Override
    boolean pushForce(Card value) throws Exception {
        return super.push(value);
    }
}
