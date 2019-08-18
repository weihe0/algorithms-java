package data_structure;

import java.util.*;

public class LinkList {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        HashMap<Node, Node> map = new HashMap<>();
        Node newHead = new Node(head.val, null, null);
        map.put(head, newHead);
        Node oldNode = head, newNode = newHead;
        while (oldNode.next != null) {
            newNode.next = new Node(oldNode.next.val, null, null);
            oldNode = oldNode.next;
            newNode = newNode.next;
            map.put(oldNode, newNode);
        }
        for (oldNode = head; oldNode != null; oldNode = oldNode.next) {
            if (oldNode.random != null) {
                newNode = map.get(oldNode);
                newNode.random = map.get(oldNode.random);
            }
        }
        return newHead;
    }
}

class Node {
    public int val;
    public Node next, random;

    public Node(int val, Node next, Node random) {
        this.val = val;
        this.next = next;
        this.random = random;
    }
}