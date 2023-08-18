package org.github.chinlinlee.dcm777.net;

import java.io.IOException;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Commands;
import org.dcm4che3.net.Dimse;
import org.dcm4che3.net.PDVInputStream;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.Status;
import org.dcm4che3.net.service.BasicCStoreSCP;
import org.dcm4che3.net.service.DicomServiceException;

public class BasicModCStoreSCP extends BasicCStoreSCP{

    private CStoreSCPInject myStoreSCPInject;

    public BasicModCStoreSCP() {
        super("*");
    }

    public BasicModCStoreSCP(String... sopClasses) {
        super(sopClasses);
    }

    public BasicModCStoreSCP(CStoreSCPInject storeSCPInject, String... sopClasses) {
        super(sopClasses);
        myStoreSCPInject = storeSCPInject;
    }

    @Override
    public void onDimseRQ(Association as, PresentationContext pc, Dimse dimse,
            Attributes rq, PDVInputStream data) throws IOException {

        myStoreSCPInject.onDimseRQ(as, pc, dimse, rq, data);
    }

    protected void store(Association as, PresentationContext pc, Attributes rq,
            PDVInputStream data, Attributes rsp) throws IOException {

        myStoreSCPInject.store(as, pc, rq, data, rsp);
    }
}
