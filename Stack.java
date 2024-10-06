public class Stack<T> {
    static final int MAX_SIZE = 256;
    protected T[] data;
    private int head = -1;

    Stack() {
        data = (T[]) new Object[MAX_SIZE];
    }

    boolean isEmpty() {
        return head == -1;
    }
    
    boolean isFull() {
        return head == MAX_SIZE;
    }

    int length() {
        return head + 1;
    }

    boolean push(T value) {
        if (isFull())
            return false;

        data[++head] = value;
        return true;
    }

    T top() {
        if (isEmpty())
            return null;
            
        return data[head];
    }

    T pop() {
        if (isEmpty())
            return null;

        T topValue = top();
        head--;
        return topValue;
    }

    void print() {
        for (int i = 0; i <= head; i++) {
            System.out.println(data[i]);
        }
    }

    protected T getIndex(int index) {
        return (T) data[index];
    }

    protected void setIndex(int index, T value) {
        data[index] = value;
    }
}
