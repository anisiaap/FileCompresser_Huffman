package HuffmanAlgo;

public class FreqCounter {
    public static int[] countFrequencies(byte[] data) {
        int[] frequencies = new int[256];
        if (data == null) {
            return frequencies;
        }
        for (byte b : data) {
            frequencies[b & 0xFF]++;
        }
        return frequencies;
    }
}
