package org.github.chinlinlee.dcm777.net;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.pdu.PresentationContext;

public interface MPPSScpInject {
    public void onNCreateRQ(Association as, PresentationContext pc, Attributes rq, Attributes rqAttrs);
    public void onNSetRQ(Association as, PresentationContext pc, Attributes rq, Attributes rqAttrs);
}
