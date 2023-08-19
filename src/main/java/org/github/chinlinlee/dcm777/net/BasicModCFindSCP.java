package org.github.chinlinlee.dcm777.net;

import java.io.IOException;
import java.util.EnumSet;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Dimse;
import org.dcm4che3.net.QueryOption;
import org.dcm4che3.net.pdu.ExtendedNegotiation;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.BasicCFindSCP;
import org.dcm4che3.net.service.DicomServiceException;
import org.dcm4che3.net.service.QueryRetrieveLevel2;
import org.dcm4che3.net.service.QueryTask;

public class BasicModCFindSCP extends BasicCFindSCP {
    private EnumSet<QueryRetrieveLevel2> qrLevels;
    CFindSCPInject myCFindSCPInject;

    public BasicModCFindSCP(String... sopClasses) {
        super(sopClasses);
    }

    public BasicModCFindSCP(CFindSCPInject findSCPInject, String sopClass,
            EnumSet<QueryRetrieveLevel2> qrLevels) {
        super(sopClass);
        myCFindSCPInject = findSCPInject;
        this.qrLevels = qrLevels;
    }

    @Override
    public void onDimseRQ(Association as, PresentationContext pc, Dimse dimse, Attributes rq,
            Attributes keys) throws IOException {
        myCFindSCPInject.onDimseRQ(as, pc, dimse, rq, keys);
    }

    protected QueryTask calculateMatches(Association as, PresentationContext pc, Attributes rq,
            Attributes keys) throws DicomServiceException {
        return myCFindSCPInject.calculateMatches(as, pc, rq, keys);
    }

    public QueryRetrieveLevel2 getQrLevel(Association as, PresentationContext pc, Attributes rq,
            Attributes keys) throws DicomServiceException {
        return QueryRetrieveLevel2.validateQueryIdentifier(keys, qrLevels, relational(as, rq),
                false);
    }

    private boolean relational(Association as, Attributes rq) {
        String cuid = rq.getString(Tag.AffectedSOPClassUID);
        ExtendedNegotiation extNeg = as.getAAssociateAC().getExtNegotiationFor(cuid);
        return QueryOption.toOptions(extNeg).contains(QueryOption.RELATIONAL);
    }
}
