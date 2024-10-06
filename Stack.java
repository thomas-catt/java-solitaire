public class Stack<T> {
    // static final int MAX_SIZE = 256;
    // protected T[] data;
    // private int head = -1;
    protected Node<T> head;
    private int length;

    Stack() {
        // data = (T[]) new Object[MAX_SIZE];
        this.length = 0;
        head = null;
    }

    boolean isEmpty() {
        return length == 0;
    }

    int length() {
        return length;
    }

    boolean push(T value) throws Exception {
        if (value == null)
            return false;
        
        Node<T> n = new Node<>(value);
        if (head != null)
            n.next = head;

        head = n;

        length++;
        return true;
    }
    
    boolean pushForce(T value) throws Exception {
        return push(value);
    }

    T top() {
        if (isEmpty())
            return null;
            
        return head.data;
    }

    T pop() {
        if (isEmpty())
            return null;

        T topValue = head.data;
        head = head.next;
            
        length--;
        return topValue;
    }

    void print() {
        Node<T> n = head;
        while (n != null) {
            System.out.println(n.data);
            n = n.next;
        }
    }

    protected T getIndex(int index) {
        Node<T> n = head;
        for (int i = 0; i < (length - index - 1); i++)
            n = n.next;

        return n.data;
    }

    protected void setIndex(int index, T value) {
        Node<T> n = head;
        for (int i = 0; i < (length - index - 1); i++)
            n = n.next;

        n.data = value;
    }
}
