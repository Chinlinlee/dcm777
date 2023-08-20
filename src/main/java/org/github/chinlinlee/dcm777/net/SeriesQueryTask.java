package org.github.chinlinlee.dcm777.net;

import java.io.IOException;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Status;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.DicomServiceException;

public class SeriesQueryTask extends StudyQueryTask {

    private SeriesQueryTaskInject mySeriesQueryTaskInject;

    public SeriesQueryTask(Association as, PresentationContext pc, Attributes rq, Attributes keys,
            QueryTaskInject queryTaskInject, PatientQueryTaskInject patientQueryTaskInject,
            StudyQueryTaskInject studyQueryTaskInject, SeriesQueryTaskInject seriesQueryTaskInject) throws DicomServiceException {

        super(as, pc, rq, keys, queryTaskInject, patientQueryTaskInject, studyQueryTaskInject);
        mySeriesQueryTaskInject = seriesQueryTaskInject;
        wrappedFindNextSeries();
    }

    private void wrappedFindNextSeries() throws DicomServiceException {
        try {
            this.mySeriesQueryTaskInject.wrappedFindNextSeries();
        } catch (IOException e) {
            throw new DicomServiceException(Status.UnableToProcess, e);
        }
    }
}
