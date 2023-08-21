package org.github.chinlinlee.dcm777.net;

import java.io.IOException;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Status;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.DicomServiceException;

public class InstanceQueryTask extends SeriesQueryTask {
    private InstanceQueryTaskInject myInstanceQueryTaskInject;

    public InstanceQueryTask(Association as, PresentationContext pc, Attributes rq, Attributes keys,
            QueryTaskInject queryTaskInject, PatientQueryTaskInject patientQueryTaskInject,
            StudyQueryTaskInject studyQueryTaskInject, SeriesQueryTaskInject seriesQueryTaskInject,
            InstanceQueryTaskInject instanceQueryTaskInject) throws DicomServiceException {
        
        super(as, pc, rq, keys, queryTaskInject, patientQueryTaskInject, studyQueryTaskInject,
                seriesQueryTaskInject);
        myInstanceQueryTaskInject = instanceQueryTaskInject;
    }

    private void wrappedFindNextInstance() throws DicomServiceException {
        try {
            this.myInstanceQueryTaskInject.wrappedFindNextInstance();
        } catch (IOException e) {
            throw new DicomServiceException(Status.UnableToProcess, e);
        }
    }

    public boolean findNextInstance() throws IOException {
        return myInstanceQueryTaskInject.findNextInstance();
    }
}
