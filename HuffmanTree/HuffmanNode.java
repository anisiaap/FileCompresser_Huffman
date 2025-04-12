package HuffmanTree;

public class HuffmanNode implements Comparable<HuffmanNode>{

    public char ch;
    int freq;
    public HuffmanNode left;
    public HuffmanNode right;

    HuffmanNode(char ch, int freq, HuffmanNode left, HuffmanNode right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return (left == null) && (right == null);
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.freq - o.freq;
    }
}
