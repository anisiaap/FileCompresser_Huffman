package HuffmanTree;

import java.util.PriorityQueue;

public class HuffmanTreeBuilder {
    public static final int R = 256;

    public static HuffmanNode buildTree(int[] freq) {
        // initialize priority queue with singleton trees
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (int i = 0; i < R; i++)
            if (freq[i] > 0)
                pq.add(new HuffmanNode((char)i, freq[i], null, null));
        // repeat merge two smallest trees
        while (pq.size() > 1) {
            HuffmanNode left = pq.remove();
            HuffmanNode right = pq.remove();
            HuffmanNode parent = new HuffmanNode('\0', left.freq + right.freq, left, right);
            pq.add(parent);
        }
        return pq.remove();
    }

    public static HuffmanNode rebuildTreeFromCodeTable(String[] codeLines) {
        HuffmanNode root = new HuffmanNode('\0', 0, null, null);
        for (String line : codeLines) {
            String[] parts = line.split(":", 2);
            if (parts.length != 2) continue;
            int sym = Integer.parseInt(parts[0]);
            String code = parts[1];
            HuffmanNode curr = root;
            for (char bit : code.toCharArray()) {
                if (bit == '0') {
                    if (curr.left == null)
                        curr.left = new HuffmanNode('\0', 0, null, null);
                    curr = curr.left;
                } else {
                    if (curr.right == null)
                        curr.right = new HuffmanNode('\0', 0, null, null);
                    curr = curr.right;
                }
            }
            curr.ch = (char) sym;
        }
        return root;
    }

}
