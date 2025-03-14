package org.github.chinlinlee.dcm777.net;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Dimse;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.BasicMPPSSCP;
import org.dcm4che3.net.service.DicomServiceException;

import java.io.IOException;

public class SimpleMPPSScp extends BasicMPPSSCP {
    MPPSScpInject myMPPSScpInject;

    public SimpleMPPSScp(MPPSScpInject inject) {
        myMPPSScpInject = inject;
    }

    public void onDimseRQ(Association as, PresentationContext pc, Dimse dimse, Attributes rq, Attributes rqAttrs) throws IOException {
        switch (dimse) {
            case N_CREATE_RQ:
                this.onNCreateRQ(as, pc, rq, rqAttrs);
                break;
            case N_SET_RQ:
                this.onNSetRQ(as, pc, rq, rqAttrs);
                break;
            default:
                throw new DicomServiceException(529);
        }

    }

    public void onNCreateRQ(Association as, PresentationContext pc, Attributes rq, Attributes rqAttrs) {
        myMPPSScpInject.onNCreateRQ(as, pc, rq, rqAttrs);
    }

    public void onNSetRQ(Association as, PresentationContext pc, Attributes rq, Attributes rqAttrs) {
        myMPPSScpInject.onNSetRQ(as, pc, rq, rqAttrs);
    }
}
