import HuffmanAlgo.Huffman;

public class FileCompressor {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("not enough argcs, use : java FileCompresser mode inputFile outputFile");
            System.exit(1);
        }
        String mode = args[0];
        String inputFile = args[1];
        String outputFile = args[2];

        System.out.println(mode + " " +  inputFile+  " "+  outputFile);
        if (mode.equals("-c")) {
            Huffman.compress(inputFile, outputFile);
        } else if (mode.equals("-d")) {
            Huffman.decompress(inputFile, outputFile);
        } else {
            System.err.println("invalid mode use -c for compression or -d for decompression");
        }
    }
}
