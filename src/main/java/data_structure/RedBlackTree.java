package data_structure;

public class RedBlackTree {
    private enum Colour {RED, BLACK}

    private class Node {
        int key;
        Colour colour;
        Node left, right, parent;

        Node(int key, Colour colour) {
            this.key = key;
            this.colour = colour;
            left = right = parent = nullNode;
        }
    }

    private Node nullNode = new Node(0, Colour.BLACK);
    private Node root = nullNode;

    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (x.right != nullNode) {
            x.right.parent = x;
        }
        y.parent = x.parent;
        if (y.parent == nullNode) {
            root = y;
        } else if (y.parent.left == x) {
            y.parent.left = y;
        } else {
            y.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;
        if (y.left != nullNode) {
            y.left.parent = y;
        }
        x.parent = y.parent;
        if (x.parent == nullNode) {
            root = x;
        } else if (x.parent.left == y) {
            x.parent.left = x;
        } else {
            x.parent.right = x;
        }
        x.right = y;
        y.parent = x;
    }

    private void insertFixup(Node z) {
        while (z.parent.colour == Colour.RED) {
            Node p = z.parent;
            Node pp = z.parent.parent;
            if (pp.left == p) {
                Node y = pp.right;
                if (y.colour == Colour.RED) {
                    p.colour = y.colour = Colour.BLACK;
                    pp.colour = Colour.RED;
                    z = pp;
                } else {

                }
            } else {

            }
        }
    }

    public void insert(Node z) {
        Node x = root;
        Node y = nullNode;
        while (x != nullNode) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        if (y == nullNode) {
            root = z;
        } else if (z.key < y.key) {
            y.left = z;
        } else {
            y.right = z;
        }
        z.left = z.right = nullNode;
        z.colour = Colour.RED;
        insertFixup(z);
    }

}