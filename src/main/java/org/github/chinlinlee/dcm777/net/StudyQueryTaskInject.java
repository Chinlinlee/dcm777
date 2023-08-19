package org.github.chinlinlee.dcm777.net;

import java.io.IOException;
import org.dcm4che3.net.service.DicomServiceException;

public interface StudyQueryTaskInject {
    void wrappedFindNextStudy() throws DicomServiceException;

    void getStudy();

    boolean findNextStudy() throws IOException;
}
