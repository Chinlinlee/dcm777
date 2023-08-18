package org.github.chinlinlee.dcm777.net;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.BasicQueryTask;
import org.dcm4che3.net.service.DicomServiceException;

public class BasicModQueryTask extends BasicQueryTask {

  QueryTaskInject myQueryTaskInject;

  public BasicModQueryTask(
    Association as,
    PresentationContext pc,
    Attributes rq,
    Attributes keys
  ) {
    super(as, pc, rq, keys);
  }

  public BasicModQueryTask(
    Association as,
    PresentationContext pc,
    Attributes rq,
    Attributes keys,
    QueryTaskInject queryTaskInject
  ) {
    super(as, pc, rq, keys);
    myQueryTaskInject = queryTaskInject;
  }

  @Override
  public boolean hasMoreMatches() throws DicomServiceException {
    return myQueryTaskInject.hasMoreMatches();
  }

  @Override
  public Attributes nextMatch() throws DicomServiceException {
    return myQueryTaskInject.nextMatch();
  }

  @Override
  protected Attributes adjust(Attributes match) throws DicomServiceException {
    return myQueryTaskInject.adjust(match);
  }
}
