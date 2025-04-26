package IO;
import java.io.IOException;
import java.io.FileOutputStream;
import IO.BitOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWriter {
    public static void writeCompressedData(String outputFile, String encodedData, String[] codeTable, int totalChars) {
        int unique = 0;
        for (String s : codeTable) if (s != null) unique++;

        try (BitOutputStream out = new BitOutputStream(new FileOutputStream(outputFile))) {
            out.writeInt(unique);
            out.writeInt(totalChars);

            // for each symbol write: byte value, code length, then code bits
            for (int sym = 0; sym < codeTable.length; sym++) {
                String code = codeTable[sym];
                if (code == null) continue;
                out.writeByte(sym);
                out.writeByte(code.length());
                for (char c : code.toCharArray()) {
                    out.writeBit(c == '1' ? 1 : 0);
                }
            }

            for (char c : encodedData.toCharArray()) {
                out.writeBit(c == '1' ? 1 : 0);
            }

        } catch (IOException e) {
            System.err.println("error writing compressed file " + outputFile);
            e.printStackTrace();
        }
    }

    public static void writeDecodedData(String outputFile, byte[] data) {
        try {
            Files.write(Paths.get(outputFile), data);
            System.out.println("decompression complete to " + outputFile);
        } catch (IOException e) {
            System.err.println("error at decompression " + outputFile);
            e.printStackTrace();
        }
    }
}
