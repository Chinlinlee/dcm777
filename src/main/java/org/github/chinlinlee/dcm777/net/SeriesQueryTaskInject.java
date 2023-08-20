package org.github.chinlinlee.dcm777.net;

import java.io.IOException;
import org.dcm4che3.net.service.DicomServiceException;

public interface SeriesQueryTaskInject {
    void wrappedFindNextSeries() throws DicomServiceException;

    void getSeries();

    boolean findNextSeries() throws IOException;
}
