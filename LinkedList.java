public class LinkedList<T> {
    private Node<T> head;
    int length;

    LinkedList() {
        head = null;
        length = 0;
    }

    void clear() {
        head = null;
        length = 0;
    }

    void append(T data) {
        Node<T> n = new Node<T>(data);
        length++;
        if (head == null) {
            head = n;
            return;
        }
        
        Node<T> target = head;
        while (target.next != null)
            target = target.next;

        target.next = n;
    }

    T valueAt(int index) {
        if (index >= length)
            return null;

        Node<T> n = head;
        for (int i = 0; i <= index; i++) {
            n = n.next;
        }

        return n.data;
    }

    void print() {
        Node<T> n = head;
        while (n != null) {
            System.out.println(n.data);
            n = n.next;
        }
    }
    
    void reverse() throws Exception {
        // create new stack and push all elements onto it
        Stack<T> s = new Stack<>();
        Node<T> n = head;
        while (n != null) {
            s.push(n.data);
            n = n.next;
        }
        
        // clear the entire linked list
        clear();
        T value = s.pop();

        // pop each value and add to list
        while (value != null) {
            append(value);
            value = s.pop();
        }
    }
}
