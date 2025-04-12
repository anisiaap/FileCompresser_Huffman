package HuffmanAlgo;

import HuffmanAlgo.FreqCounter;
import HuffmanTree.HuffmanTreeBuilder;
import HuffmanTree.HuffmanNode;
import HuffmanAlgo.Encoder;
import IO.FileReader;
import IO.FileWriter;

public class Huffman {

    public static void compress(String inputFile, String outputFile) {

        byte[] fileBytes = FileReader.readBytes(inputFile);
        if (fileBytes == null) {
            System.err.println("Error reading file: " + inputFile);
            return;
        }
        int[] frequencies = FreqCounter.countFrequencies(fileBytes);

        HuffmanNode root = HuffmanTreeBuilder.buildTree(frequencies);

        String[] codeTable = CodeTableGenerator.generateCodeTable(root);

        String encodedData = Encoder.encode(fileBytes, codeTable);

        FileWriter.writeCompressedData(outputFile, encodedData);

        System.out.println("Compression complete. Output saved to: " + outputFile);
    }

    public static void decompress(String inputFile, String outputFile) {
    }
}
