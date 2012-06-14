import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: pyoussef
 * Date: 4/21/12
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class BinTreeBalanceChecker {
    public static void main(String[] argv) {
        Integer[] tc1 = {5,4,1,10,2,4,9,1};
        bstBalanceTest(tc1);
        Integer[] tc2 = {5,3,10,2,4,9,11};
        bstBalanceTest(tc2);
    }

    private static void bstBalanceTest(Integer[] treeValues) {
        System.out.println("-- Test case --");
        BinSearchTree bt = new BinSearchTree();
        for (Integer i : treeValues) {
            bt.add(i);
        }
        bt.printTree();
        System.out.println("is Balanced? " + Boolean.toString(isBalanced(bt.getHead())));
    }

    private static boolean isBalanced(Node n) {
        Queue<NodeWithLevel> q = new LinkedList<NodeWithLevel>();
        q.add(new NodeWithLevel(n, 0));
        Integer min = Integer.MAX_VALUE;
        Integer max = 0;

        while (q.peek() != null) {
            NodeWithLevel nl = q.remove();
            if (nl.node.left == null && nl.node.right == null) {
                if (nl.level < min)
                    min = nl.level;
                if (nl.level > max)
                    max = nl.level;
            }
            if (nl.node.left != null)
                q.add(new NodeWithLevel(nl.node.left, nl.level + 1));
            if (nl.node.right != null)
                q.add(new NodeWithLevel(nl.node.right, nl.level + 1));
        }

        return (max - min) >= 1 ? false : true;
    }
}

class BinSearchTree {
    Node head;

    public void add(int i) {
        if (head == null) {
            head = new Node(i);
            return;
        }

        Node next = head;
        while (true) {
            if (i >= next.data) {
                if (next.right == null) {
                    next.right = new Node(i);
                    break;
                } else {
                    next = next.right;
                }
            } else {
                if (next.left == null) {
                    next.left = new Node(i);
                    break;
                } else {
                    next = next.left;
                }
            }
        }
    }

    public void printTree() {
        Queue<NodeWithLevel> nodes = new LinkedList<NodeWithLevel>();
        ArrayList<NodeWithLevel> nodeList = new ArrayList<NodeWithLevel>();
        nodes.add(new NodeWithLevel(head, 0));
        nodeList.add(new NodeWithLevel(head, 0));


        while (nodes.peek() != null) {
            NodeWithLevel n = nodes.remove();
            if (n.node.left != null) {
                nodes.add(new NodeWithLevel(n.node.left, n.level + 1));
                nodeList.add(new NodeWithLevel(n.node.left, n.level + 1));
            }
            if (n.node.right != null) {
                nodes.add(new NodeWithLevel(n.node.right, n.level + 1));
                nodeList.add(new NodeWithLevel(n.node.right, n.level + 1));
            }
        }

        int lastLevel = 0;
        for (int i = 0; i < nodeList.size(); i++) {
            NodeWithLevel n = nodeList.get(i);
            if (lastLevel < n.level)
                System.out.print("\n");
            System.out.print(n.node.data + " ");
            lastLevel = n.level;
        }
        System.out.print("\n");
    }

    public Node getHead() {
        return head;
    }
}

class Node {
    public int data;
    public Node left;
    public Node right;

    public Node(int d) {
        data = d;
    }
}

class NodeWithLevel {
    public int level;
    public Node node;

    public NodeWithLevel(Node n, int l) {
        node = n;
        level = l;
    }
}
