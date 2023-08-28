package org.github.chinlinlee.dcm777.dcmqrscp;

public interface RetrieveAuditInject {
    void onBeginTransferringDICOMInstances(String[] studyInstanceUIDs);

    void onDicomInstancesTransferred(String[] studyInstanceUIDs);

    void setEventResult(String eventResult);
}
