package org.github.chinlinlee.dcm777.dcmqrscp;

public interface RetrieveAuditInject {
    void onBeginTransferringDICOMInstances(String studyInstanceUID);

    void onDicomInstancesTransferred(String studyInstanceUID);

    void setEventResult(String eventResult);
}
