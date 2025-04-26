package HuffmanAlgo;

import HuffmanAlgo.FreqCounter;
import HuffmanTree.HuffmanTreeBuilder;
import HuffmanTree.HuffmanNode;
import HuffmanAlgo.Encoder;
import IO.FileReader;
import IO.FileWriter;
import IO.BitInputStream;
import java.io.FileInputStream;
import HuffmanAlgo.Decoder;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;

public class Huffman {

    public static void compress(String inputFile, String outputFile) {

        byte[] fileBytes = FileReader.readBytes(inputFile);
        if (fileBytes == null) {
            System.err.println("error reading file " + inputFile);
            return;
        }

        int[] frequencies = FreqCounter.countFrequencies(fileBytes);

        HuffmanNode root = HuffmanTreeBuilder.buildTree(frequencies);

        String [] codeTable = CodeTableGenerator.generateCodeTable(root);

        String encodedData = Encoder.encode(fileBytes, codeTable);
        int totalChars = fileBytes.length;
        FileWriter.writeCompressedData(outputFile, encodedData, codeTable, totalChars);

        System.out.println("compression done " + outputFile);
    }

    public static void decompress(String inputFile, String outputFile) {
        try (BitInputStream in = new BitInputStream(new FileInputStream(inputFile))) {
            //parse file
            int uniqueCount = in.readInt();
            int totalChars  = in.readInt();
            //rebuild code table
            String[] codeLines = new String[uniqueCount];
            for (int i = 0; i < uniqueCount; i++) {
                int sym    = in.readByte() & 0xFF;
                int length = in.readByte() & 0xFF;
                StringBuilder sb = new StringBuilder(length);
                for (int j = 0; j < length; j++) {
                    sb.append(in.readBit() == 0 ? '0' : '1');
                }
                codeLines[i] = sym + ":" + sb;
            }

            HuffmanNode root = HuffmanTreeBuilder.rebuildTreeFromCodeTable(codeLines);

            StringBuilder bits = new StringBuilder();
            try {
                while (true) {
                    bits.append(in.readBit() == 0 ? '0' : '1');
                }
            } catch (EOFException eof) {
            }


            byte[] decoded = Decoder.decode(bits.toString(), root, totalChars);
            FileWriter.writeDecodedData(outputFile, decoded);

            System.out.println("decompression done " + outputFile);

        } catch (Exception e) {
            System.err.println("error during decompress " + e.getMessage());
            e.printStackTrace();
        }
    }
}
