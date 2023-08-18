package org.github.chinlinlee.dcm777.net;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.service.DicomServiceException;

public interface QueryTaskInject {
    public boolean hasMoreMatches() throws DicomServiceException;

    public Attributes nextMatch() throws DicomServiceException;

    public Attributes adjust(Attributes match) throws DicomServiceException;

}
