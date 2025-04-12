package HuffmanTree;

public class HuffmanNode implements Comparable<HuffmanNode>{

    char ch;
    int freq;
    HuffmanNode left, right;

    HuffmanNode(char ch, int freq, HuffmanNode left, HuffmanNode right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    boolean isLeaf() {
        return (left == null) && (right == null);
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return 0;
    }
}
