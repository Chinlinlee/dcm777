package org.github.chinlinlee.dcm777.net;

import java.io.IOException;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Status;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.DicomServiceException;

public class StudyQueryTask extends PatientQueryTask {

    StudyQueryTaskInject myStudyQueryTaskInject;

    public StudyQueryTask(Association as, PresentationContext pc, Attributes rq, Attributes keys,
            QueryTaskInject queryTaskInject, PatientQueryTaskInject patientQueryTaskInject,
            StudyQueryTaskInject studyQueryTaskInject) throws DicomServiceException {
        super(as, pc, rq, keys, queryTaskInject, patientQueryTaskInject);
        myStudyQueryTaskInject = studyQueryTaskInject;
        wrappedFindNextStudy();
    }

    private void wrappedFindNextStudy() throws DicomServiceException {
        try {
            this.myStudyQueryTaskInject.findNextStudy();
        } catch (IOException e) {
            throw new DicomServiceException(Status.UnableToProcess, e);
        }
    }
}
