package HuffmanAlgo;

import HuffmanTree.HuffmanNode;
import java.io.ByteArrayOutputStream;

public class Decoder {

    public static byte[] decode(String bits, HuffmanNode root, int totalChars) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        HuffmanNode curr = root;
        int count = 0;
        for (int i = 0; i < bits.length() && count < totalChars; i++) {
            curr = (bits.charAt(i) == '0') ? curr.left : curr.right;
            if (curr.isLeaf()) {
                out.write((byte) curr.ch);
                count++;
                curr = root;
            }
        }
        return out.toByteArray();
    }
}
