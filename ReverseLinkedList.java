package practice; 

class Node2 {
    String s;
    Node2 previous;
    Node2 next;

    //Constructor
    Node2(String s) {
        this.s = s;
        this.previous = this.next = null;
    }
}
class LinkedList {
    Node2 head;

    void append(String s) {
        Node2 newNode = new Node2(s);

        if (head == null) {
            head = newNode;
        }

        Node2 cur = head;
        if (cur.next != null) {
            cur = cur.next;
        }

        cur.next = newNode;

    }

    void reverse() {  

        Node2 cur = head;
        Node2 next = null, prev = null;

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        
        head = prev;

        print();
    }

    public void print() {
        if (head == null) return;

        Node2 cur = head;
        while (cur != null) {
            System.out.print(cur.s + "->");
            cur = cur.next;
        }

        System.out.println("null");
        
    }
}

public class ReverseLinkedList {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.append("A");
        list.append("B");
        list.append("C");

        System.out.print("List before reverse: ");
        list.print();
        System.out.print("Reversed list: ");
        list.reverse();

    }
}