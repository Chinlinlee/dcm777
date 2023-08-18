package org.github.chinlinlee.dcm777.net;

import java.io.IOException;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Dimse;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.BasicCFindSCP;
import org.dcm4che3.net.service.DicomServiceException;
import org.dcm4che3.net.service.QueryTask;

public class BasicModCFindSCP extends BasicCFindSCP {

  CFindSCPInject myCFindSCPInject;

  public BasicModCFindSCP(String... sopClasses) {
    super(sopClasses);
  }

  public BasicModCFindSCP(CFindSCPInject findSCPInject, String... sopClasses) {
    super(sopClasses);
    myCFindSCPInject = findSCPInject;
  }

  @Override
  public void onDimseRQ(
    Association as,
    PresentationContext pc,
    Dimse dimse,
    Attributes rq,
    Attributes keys
  ) throws IOException {
    myCFindSCPInject.onDimseRQ(as, pc, dimse, rq, keys);
  }

  protected QueryTask calculateMatches(
    Association as,
    PresentationContext pc,
    Attributes rq,
    Attributes keys
  ) throws DicomServiceException {
    return myCFindSCPInject.calculateMatches(as, pc, rq, keys);
  }
}
