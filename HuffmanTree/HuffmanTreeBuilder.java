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

}
