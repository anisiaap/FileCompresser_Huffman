package HuffmanAlgo;

import HuffmanTree.HuffmanNode;

import java.util.HashMap;
import java.util.Map;

import static HuffmanTree.HuffmanTreeBuilder.R;

public class CodeTableGenerator {
    public static Map<Character, String> generateCodeTable(HuffmanNode root) {
        String[] st = new String[R];
        buildCodeTable(st, root, "");
    }
    private static void buildCodeTable(String[] table, HuffmanNode node, String code) {
        if (node == null) return;
        if (node.isLeaf()) {
            table[node.ch] = code;  // node.ch is cast implicitly to an int index
            return;
        }
        buildCodeTable(table,  node.left,  code + "0");
        buildCodeTable(table, node.right, code + "1");
    }
}

