package HuffmanAlgo;

import HuffmanTree.HuffmanNode;


import static HuffmanTree.HuffmanTreeBuilder.R;

public class CodeTableGenerator {
    public static String[] generateCodeTable(HuffmanNode root) {
        String[] st = new String[R];
        buildCodeTable(st, root, "");
        return st;
    }

    private static void buildCodeTable(String[] table, HuffmanNode node, String code) {
        if (node == null) return;
        if (node.isLeaf()) {
            table[node.ch] = code;
            return;
        }
        buildCodeTable(table, node.left, code + "0");
        buildCodeTable(table, node.right, code + "1");
    }


}

