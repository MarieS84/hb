package shb.poc.systemmap.v1;

//interface specifies behaviour of SystemMapManagerImpl class

public interface SystemMapManager {

    public void createBusinessSystem(String name, String description, String documentationUrl);

    public void createRuntime(Long systemId, String runtimeName, String description);

    public void createService(Long systemId, String serviceName, String description);

    public void createSystemIntegration(Long systemId, String integrationName, String description, Long consumerService, Long producerService);

    public void deleteBusinessSystem(Long systemId);

    public void deleteRuntime(Long runtimeId);

    public void deleteService(Long serviceId);

    public void deleteSystemIntegration(Long systemIntegrationId);

    public void listBusinessSystems();

    public void updateBusinessSystems(Long systemId, String updateItem, String change);

}
