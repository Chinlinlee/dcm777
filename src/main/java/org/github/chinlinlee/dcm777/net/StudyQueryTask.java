package org.github.chinlinlee.dcm777.net;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.pdu.PresentationContext;

public class StudyQueryTask extends PatientQueryTask {

  StudyQueryTaskInject myStudyQueryTaskInject;

  StudyQueryTask(
    Association as,
    PresentationContext pc,
    Attributes rq,
    Attributes keys,
    QueryTaskInject queryTaskInject
  ) {
    super(as, pc, rq, keys, queryTaskInject);
  }

  StudyQueryTask(
    Association as,
    PresentationContext pc,
    Attributes rq,
    Attributes keys,
    QueryTaskInject queryTaskInject,
    StudyQueryTaskInject studyQueryTaskInject
  ) {
    super(as, pc, rq, keys, queryTaskInject);
    myStudyQueryTaskInject = studyQueryTaskInject;
  }
}
