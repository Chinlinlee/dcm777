package org.github.chinlinlee.dcm777.net;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.DicomServiceException;

public class StudyQueryTask extends PatientQueryTask {

  StudyQueryTaskInject myStudyQueryTaskInject;

  StudyQueryTask(
    Association as,
    PresentationContext pc,
    Attributes rq,
    Attributes keys,
    QueryTaskInject queryTaskInject,
    QueryTaskOptions queryTaskOptions,
    PatientQueryTaskInject patientQueryTaskInject,
    StudyQueryTaskInject studyQueryTaskInject
  ) throws DicomServiceException {
    super(
      as,
      pc,
      rq,
      keys,
      queryTaskInject,
      queryTaskOptions,
      patientQueryTaskInject
    );
    myStudyQueryTaskInject = studyQueryTaskInject;
  }
}
