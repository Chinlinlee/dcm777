package org.github.chinlinlee.dcm777.net;

import java.io.IOException;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.UID;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Commands;
import org.dcm4che3.net.Dimse;
import org.dcm4che3.net.Status;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.AbstractDicomService;
import org.dcm4che3.net.service.DicomServiceException;

public class SimpleStgCmtSCP extends AbstractDicomService {

    private final StgCmtSCPInject myStgCmtSCPInject;

    public SimpleStgCmtSCP(StgCmtSCPInject stgCmtSCPInject) {
        super(UID.StorageCommitmentPushModel);
        this.myStgCmtSCPInject = stgCmtSCPInject;
    }

    @Override
    public void onDimseRQ(Association as, PresentationContext pc, Dimse dimse, Attributes rq,
            Attributes actionInfo) throws IOException {
        if (dimse != Dimse.N_ACTION_RQ)
            throw new DicomServiceException(Status.UnrecognizedOperation);

        int actionTypeID = rq.getInt(Tag.ActionTypeID, 0);
        if (actionTypeID != 1)
            throw new DicomServiceException(Status.NoSuchActionType).setActionTypeID(actionTypeID);

        this.myStgCmtSCPInject.onDimseRQ(as, pc, dimse, rq, actionInfo);
    }
}
