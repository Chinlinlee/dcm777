package org.github.chinlinlee.dcm777.net;

import java.io.IOException;
import org.dcm4che3.net.service.DicomServiceException;

public interface PatientQueryTaskInject {
  void wrappedFindNextPatient() throws DicomServiceException;

  void getPatient();

  boolean findNextPatient() throws IOException;
}
