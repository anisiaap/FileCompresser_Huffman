package IO;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import java.io.Closeable;

public class BitInputStream implements Closeable{
    private final InputStream in;
    private int currentByte;
    private int numBitsRemaining;

    public BitInputStream(InputStream in) {
        this.in = in;
        this.numBitsRemaining = 0;
    }

    public int readBit() throws IOException {
        if (numBitsRemaining == 0) {
            currentByte = in.read();
            if (currentByte < 0) throw new EOFException();
            numBitsRemaining = 8;
        }
        numBitsRemaining--;
        return (currentByte >>> numBitsRemaining) & 1;
    }

    public int readByte() throws IOException {
        int b = 0;
        for (int i = 0; i < 8; i++) {
            b = (b << 1) | readBit();
        }
        return b;
    }

    public int readInt() throws IOException {
        int x = 0;
        for (int i = 0; i < 4; i++) {
            x = (x << 8) | readByte();
        }
        return x;
    }

    public void close() throws IOException {
        in.close();
    }
}
