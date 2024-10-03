package org.github.chinlinlee.dcm777.net;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Dimse;
import org.dcm4che3.net.PDVInputStream;
import org.dcm4che3.net.pdu.PresentationContext;

public interface CStoreSCPInject {

        public boolean preDimseRQ(Association as, PresentationContext pc, Dimse dimse, Attributes rq,
                PDVInputStream data);

    public void postDimseRQ(Association as, PresentationContext pc, Dimse dimse, Attributes rq,
            PDVInputStream data, Attributes rsp);

    public void postStore(Association as, PresentationContext pc, Attributes rq, InputStream data,
            Attributes rsp, File file);

}
