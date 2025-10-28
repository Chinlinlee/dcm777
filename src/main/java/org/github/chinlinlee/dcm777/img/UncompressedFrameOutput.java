/*
 * *** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is part of dcm4che, an implementation of DICOM(TM) in
 * Java(TM), hosted at https://github.com/dcm4che.
 *
 * The Initial Developer of the Original Code is
 * J4Care.
 * Portions created by the Initial Developer are Copyright (C) 2013
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 * See @authors listed below
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 *
 * *** END LICENSE BLOCK *****
 */

package org.github.chinlinlee.dcm777.img;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.imageio.codec.ImageDescriptor;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.util.StreamUtils;
import org.dcm4che3.util.SafeClose;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.FileSystems;

/**
 * <h2>Original info from dcm4che</h2>
 * <p>
 *   This class is modified to support the extraction of uncompressed frame from DICOM file
 * </p>
 * @author Gunter Zeilinger <gunterze@gmail.com>
 * @since Apr 2016
 * 
 * 
 */
public class UncompressedFrameOutput implements Closeable {
    private final Path dicomPath;
    private final int frameNumber;
    private DicomInputStream dis;
    private int frameLength;

    public UncompressedFrameOutput(String dicomPath, int frame) {
        this.dicomPath = FileSystems.getDefault().getPath(dicomPath);
        this.frameNumber = frame;
    }

    public void write(OutputStream out) throws IOException {
        try {
            initDicomInputStream();
            skipToFrame();

            StreamUtils.copy(dis, out, frameLength);
        } finally {
            close();
        }
    }

    private void initDicomInputStream() throws IOException {
        if (!Files.exists(dicomPath)) {
            throw new IOException("DICOM file not found: " + dicomPath);
        }

        dis = new DicomInputStream(dicomPath.toFile());
        Attributes attrs = dis.readDataset(o -> o.tag() >= Tag.FloatPixelData);
        frameLength = new ImageDescriptor(attrs).getFrameLength();

        int tag = dis.tag();

        if (tag != Tag.PixelData && tag != Tag.FloatPixelData && tag != Tag.DoubleFloatPixelData) {
            throw new IOException("Missing pixel data in DICOM file: " + dicomPath);
        }
    }

    private void skipToFrame() throws IOException {
        int currentFrame = 1;
        while (currentFrame < frameNumber) {
            dis.skipFully(frameLength);
            currentFrame++;
        }
    }

    @Override
    public void close() throws IOException {
        SafeClose.close(dis);
        dis = null;
    }
}
