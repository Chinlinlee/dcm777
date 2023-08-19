package org.github.chinlinlee.dcm777.net;

import java.io.File;
import java.io.IOException;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.io.DicomOutputStream;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Commands;
import org.dcm4che3.net.Dimse;
import org.dcm4che3.net.PDVInputStream;
import org.dcm4che3.net.Status;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.BasicCStoreSCP;
import org.dcm4che3.net.service.DicomServiceException;
import org.dcm4che3.util.SafeClose;

public class SimpleCStoreSCP extends BasicCStoreSCP {
    private CStoreSCPInject myStoreSCPInject;
    private File storageDir;

    public SimpleCStoreSCP() {
        super("*");
    }

    public SimpleCStoreSCP(String... sopClasses) {
        super(sopClasses);
    }

    /**
     * 
     * @param storeSCPInject 
     * @param storageDir Your storage directory
     * @param sopClasses
     */
    public SimpleCStoreSCP(CStoreSCPInject storeSCPInject, File storageDir, String... sopClasses) {
        super(sopClasses);
        myStoreSCPInject = storeSCPInject;
        this.storageDir = storageDir;
    }

    @Override
    public void onDimseRQ(Association as, PresentationContext pc, Dimse dimse, Attributes rq,
            PDVInputStream data) throws IOException {

        if (dimse != Dimse.C_STORE_RQ) {
            throw new DicomServiceException(Status.UnrecognizedOperation);
        }

        Attributes rsp = Commands.mkCStoreRSP(rq, Status.Success);
        this.store(as, pc, rq, data, rsp);
        myStoreSCPInject.postDimseRQ(as, pc, dimse, rq, data, rsp);
    }

    @Override
    protected void store(Association as, PresentationContext pc, Attributes rq, PDVInputStream data,
            Attributes rsp) throws IOException {

        String cuid = rq.getString(Tag.AffectedSOPClassUID);
        String iuid = rq.getString(Tag.AffectedSOPInstanceUID);
        String tsuid = pc.getTransferSyntax();
        File file = new File(storageDir, iuid);
        try {
            Attributes fmi = as.createFileMetaInformation(iuid, cuid, tsuid);
            storeTo(as, fmi, data, file);
            myStoreSCPInject.postStore(as, pc, rq, data, rsp, file);
        } catch (Exception e) {
            throw new DicomServiceException(Status.ProcessingFailure, e);
        }
    }

    public void storeTo(Association as, Attributes fmi, PDVInputStream data, File file)
            throws IOException {
        
        DicomOutputStream out = new DicomOutputStream(file);
        try {
            out.writeFileMetaInformation(fmi);
            data.copyTo(out);
        } finally {
            SafeClose.close(out);
        }
    }
}
