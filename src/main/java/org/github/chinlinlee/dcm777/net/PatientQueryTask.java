package org.github.chinlinlee.dcm777.net;

import java.io.IOException;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Status;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.DicomServiceException;

public class PatientQueryTask extends BasicModQueryTask {

    PatientQueryTaskInject myPatientQueryTaskInject;

    public PatientQueryTask(Association as, PresentationContext pc, Attributes rq, Attributes keys,
            QueryTaskInject queryTaskInject, PatientQueryTaskInject patientQueryTaskInject)
            throws DicomServiceException {
        super(as, pc, rq, keys, queryTaskInject);
        myPatientQueryTaskInject = patientQueryTaskInject;
        wrappedFindNextPatient();
    }

    private void wrappedFindNextPatient() throws DicomServiceException {
        try {
            myPatientQueryTaskInject.wrappedFindNextPatient();
        } catch (IOException e) {
            throw new DicomServiceException(Status.UnableToProcess, e);
        }
    }

    protected void getPatient() {
        myPatientQueryTaskInject.getPatient();
    }

    public boolean findNextPatient() throws IOException {
        return myPatientQueryTaskInject.findNextPatient();
    }
}
