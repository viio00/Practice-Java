class Node {
    int data;
    Node previous;
    Node next;

    //Constructor
    Node(int data) {
        this.data = data;
        this.previous = this.next = null;
    }
}

class DoublyLinkedList {
    Node head;

    public void append(int data) {
        Node newNode = new Node(data);

        if(head == null) {
            head = newNode;
            return;
        }

        Node cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }

        cur.next = newNode;
        newNode.previous = cur;
    }

    public void prepend(int data) {
        Node newNode = new Node(data);
        newNode.next = head;    
        if (head != null) {
            head.previous = newNode;
        }
        head = newNode;
    }

    public void addAt(int data, int val) {
        Node newNode = new Node(data);

        if (head == null) {
            System.out.println("cannot be done.");
        }

        Node cur = head;
        while (cur.next != null && cur.data != val){
            cur = cur.next;
        }

        newNode.next = cur.next;
        newNode.previous = cur;

        if (cur.next != null) {
            cur.next.previous = newNode;
        }

        cur.next = newNode;
    }

    public int length() {
        int len = 0;
        Node num = head;
        while (num != null) {
            len++;
            num = num.next;
        }
        return len;
    }

    public void printForward() {
        if (head == null) return;

        Node cur = head;
        while (cur != null) {
            System.out.print(cur.data + "->");
            cur = cur.next;
        }

        System.out.println("null");
        
    }

    public void printBackward() {
        if (head == null) return;

        Node cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }

        while (cur != null) {
            System.out.print(cur.data + "->");
            cur = cur.previous;
        }
        System.out.println("null") ;
        
    }

}

public class DoublyLinkedListPrctc {
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.append(30);
        list.prepend(20);
        list.append(40);
        list.prepend(10);
        list.addAt(5, 10);

        list.printForward();
        list.printBackward();
    }
}
