package HuffmanAlgo;

public class Encoder {
    public static String encode(byte[] fileBytes, String[] codeTable) {
        StringBuilder encodedBuilder = new StringBuilder();
        for (byte b : fileBytes) {
            int index = b & 0xFF;
            String code = codeTable[index];
            encodedBuilder.append(code);
        }
        return encodedBuilder.toString();
    }
}
