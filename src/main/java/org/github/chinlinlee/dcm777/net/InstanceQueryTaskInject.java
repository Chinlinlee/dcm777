package org.github.chinlinlee.dcm777.net;

import java.io.IOException;
import org.dcm4che3.net.service.DicomServiceException;

public interface InstanceQueryTaskInject {
    void wrappedFindNextInstance() throws DicomServiceException;

    void getInstance();

    boolean findNextInstance() throws IOException;
}
