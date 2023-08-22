package org.github.chinlinlee.dcm777.net;

import java.util.EnumSet;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.QueryOption;
import org.dcm4che3.net.pdu.ExtendedNegotiation;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.BasicCGetSCP;
import org.dcm4che3.net.service.DicomServiceException;
import org.dcm4che3.net.service.QueryRetrieveLevel2;
import org.dcm4che3.net.service.RetrieveTask;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;

public class SimpleCGetSCP extends BasicCGetSCP {
    private final EnumSet<QueryRetrieveLevel2> qrLevels;
    private CGetSCPInject myCGetSCPInject;

    public SimpleCGetSCP(CGetSCPInject cGetSCPInject, String sopClass,
            EnumSet<QueryRetrieveLevel2> qrLevels) {
        super(sopClass);
        this.qrLevels = qrLevels;
        this.myCGetSCPInject = cGetSCPInject;
    }

    @Override
    protected RetrieveTask calculateMatches(Association as, PresentationContext pc, Attributes rq,
            Attributes keys) throws DicomServiceException {
        QueryRetrieveLevel2.validateRetrieveIdentifier(keys, qrLevels, relational(as, rq), false);
        return this.myCGetSCPInject.calculateMatches(as, pc, rq, keys);
    }

    public EnumSet<QueryRetrieveLevel2> getQrLevels() {
        return qrLevels;
    }

    private boolean relational(Association as, Attributes rq) {
        String cuid = rq.getString(Tag.AffectedSOPClassUID);
        ExtendedNegotiation extNeg = as.getAAssociateAC().getExtNegotiationFor(cuid);
        return QueryOption.toOptions(extNeg).contains(QueryOption.RELATIONAL);
    }
}
