package IO;

import java.io.IOException;
import java.io.OutputStream;


import java.io.Closeable;

public class BitOutputStream implements Closeable{
    private final OutputStream out;
    private int currentByte;
    private int numBitsFilled;

    public BitOutputStream(OutputStream out) {
        this.out = out;
        this.currentByte = 0;
        this.numBitsFilled = 0;
    }

    public void writeBit(int b) throws IOException {
        if (b != 0 && b != 1) throw new IllegalArgumentException("Bit must be 0 or 1");
        currentByte = (currentByte << 1) | b;
        numBitsFilled++;
        if (numBitsFilled == 8) {
            out.write(currentByte);
            numBitsFilled = 0;
            currentByte = 0;
        }
    }

    public void writeByte(int x) throws IOException {
        for (int i = 7; i >= 0; i--) {
            writeBit((x >>> i) & 1);
        }
    }

    public void writeInt(int x) throws IOException {
        writeByte((x >>> 24) & 0xFF);
        writeByte((x >>> 16) & 0xFF);
        writeByte((x >>> 8) & 0xFF);
        writeByte((x) & 0xFF);
    }

    public void close() throws IOException {
        while (numBitsFilled != 0) {
            writeBit(0);
        }
        out.close();
    }
}
