package org.github.chinlinlee.dcm777.net;

import org.dcm4che3.net.Association;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.DicomServiceException;
import org.dcm4che3.net.service.RetrieveTask;
import org.dcm4che3.data.Attributes;

public interface CGetSCPInject {
    public RetrieveTask calculateMatches(Association as, PresentationContext pc,
                Attributes rq, Attributes keys) throws DicomServiceException;
}
