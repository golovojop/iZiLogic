package s.yarlykov.izilogic.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class NodeT<T extends Comparable<T>> {
    NodeT<T> left, right;
    T data;

    public NodeT(T data) {
        this.data = data;
    }
}

/**
 * Src: https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
 */
public class BTreePrinter {
    public static <T extends Comparable<T>> void printNodeT(NodeT<T> root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeTInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<T>> void printNodeTInternal(List<NodeT<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<NodeT<T>> newNodeTs = new ArrayList<NodeT<T>>();
        for (NodeT<T> node : nodes) {
            if (node != null) {
                System.out.print(node.data);
                newNodeTs.add(node.left);
                newNodeTs.add(node.right);
            } else {
                newNodeTs.add(null);
                newNodeTs.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= edgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(edgeLines + edgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(edgeLines + edgeLines - i);
            }

            System.out.println("");
        }

        printNodeTInternal(newNodeTs, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<T>> int maxLevel(NodeT<T> node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }
}
