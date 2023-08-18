package org.github.chinlinlee.dcm777.net;

import java.io.IOException;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Dimse;
import org.dcm4che3.net.PDVInputStream;
import org.dcm4che3.net.pdu.PresentationContext;

public interface CStoreSCPInject {
    public void onDimseRQ(Association as, PresentationContext pc, Dimse dimse,
            Attributes rq, PDVInputStream data);

    public void store(Association as, PresentationContext pc, Attributes rq,
            PDVInputStream data, Attributes rsp);

}
