package org.github.chinlinlee.dcm777.img;

import org.dcm4che3.io.DicomInputStream;

import java.io.IOException;
import java.io.OutputStream;

public class DecompressFramesOutput extends  DecompressSupport {
    private int frame;

    public DecompressFramesOutput(DicomInputStream dis, int frame) {
        super(dis);
        this.frame = frame;
    }

    public void write(OutputStream out) throws IOException {
        try {
            initEncapsulatedPixelData();

            int targetFrame = frame;
            decompressFrame(targetFrame);
            writeFrameTo(out);
        } catch (IOException e) {
            close();
            throw e;
        } finally {
            close();
        }
    }
}
