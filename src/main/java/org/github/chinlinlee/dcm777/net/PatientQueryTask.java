package org.github.chinlinlee.dcm777.net;

import java.io.IOException;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Status;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.DicomServiceException;

public class PatientQueryTask extends BasicModQueryTask {

  PatientQueryTaskInject myPatientQueryTaskInject;
  protected final String availability;
  protected final boolean ignoreCaseOfPN;
  protected final boolean matchNoValue;
  protected final int delayCFind;

  PatientQueryTask(
    Association as,
    PresentationContext pc,
    Attributes rq,
    Attributes keys,
    QueryTaskInject queryTaskInject,
    QueryTaskOptions queryTaskOptions,
    PatientQueryTaskInject patientQueryTaskInject
  ) throws DicomServiceException {
    super(as, pc, rq, keys, queryTaskInject);
    myPatientQueryTaskInject = patientQueryTaskInject;
    this.availability = queryTaskOptions.availability;
    this.ignoreCaseOfPN = queryTaskOptions.isIgnoreCaseOfPN;
    this.matchNoValue = queryTaskOptions.isMatchNoValue;
    this.delayCFind = queryTaskOptions.delayCFind;
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

  protected boolean findNextPatient() throws IOException {
    return myPatientQueryTaskInject.findNextPatient();
  }
}
