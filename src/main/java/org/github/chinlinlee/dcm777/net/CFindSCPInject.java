package org.github.chinlinlee.dcm777.net;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Dimse;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.QueryTask;

public interface CFindSCPInject {
    public void onDimseRQ(Association as, PresentationContext pc, Dimse dimse,
            Attributes rq, Attributes keys);

    public QueryTask calculateMatches(Association as, PresentationContext pc,
            Attributes rq, Attributes keys);
}
