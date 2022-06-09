package shb.poc.systemmap.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

//Service class where the interface methods are overridden to handle what is needed in this specific class
//all repositories are autowired in to be able to manipulate the database tables

@Component
@Service
public class SystemMapManagerImpl implements SystemMapManager{
    @Autowired
    BusinessSystemRepository businessSystemRepository;

    @Autowired
    RuntimeRepository runtimeRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    SystemIntegrationRepository systemIntegrationRepository;


    /**
     * Asks user for name, description and documentationUrl.
     * Creates Business System and saves in database
     */
    @Override
    public void createBusinessSystem(String name, String description, String documentationUrl) {
        BusinessSystem businessSystem = new BusinessSystem();
        businessSystem.setName(name);
        businessSystem.setDescription(description);
        businessSystem.setDocumentationUrl(documentationUrl);
        businessSystemRepository.save(businessSystem);
    }

    /**
     * Asks user for systemId to attach runtime to, name and description.
     * Creates Runtime and saves in a joint table (business system and runtime)
     */
    @Override
    public void createRuntime(Long systemId, String runtimeName, String description){

        BusinessSystem businessSystem = businessSystemRepository.findById(systemId).get();
        Runtime runtime = new Runtime();
        runtime.setName(runtimeName);
        runtime.setDescription(description);
        businessSystem.setRuntime(runtime);
        runtimeRepository.save(runtime);
        businessSystemRepository.save(businessSystem);

    }

    /**
     * Asks user for systemId to attach service to, name and description.
     * Creates Service and saves in a joint table (system and service)
     */
    @Override
    public void createService(Long systemId, String serviceName, String description) {

        try{
            BusinessSystem businessSystem = businessSystemRepository.findById(systemId).get();
            shb.poc.systemmap.v1.Service service = new shb.poc.systemmap.v1.Service();
            service.setName(serviceName);
            service.setDescription(description);
            businessSystem.setService(service);
            serviceRepository.save(service);
            businessSystemRepository.save(businessSystem);
        }catch(Exception e){
            System.out.println("Invalid systemId or input");
        }

    }

    /**
     * Asks user for systemId to attach system integration to, name, description, consumerService and producerService.
     * Creates System Integration and saves in a joint table (business system and system integration)
     */
    @Override
    public void createSystemIntegration(Long systemId, String integrationName, String description, Long consumerService, Long producerService) {
        BusinessSystem businessSystem = businessSystemRepository.findById(systemId).get();
        SystemIntegration systemIntegration = new SystemIntegration();
        systemIntegration.setName(integrationName);
        systemIntegration.setDescription(description);
        systemIntegration.setConsumerService(consumerService);
        systemIntegration.setProducerService(producerService);
        businessSystem.setSystemIntegration(systemIntegration);
        systemIntegrationRepository.save(systemIntegration);
        businessSystemRepository.save(businessSystem);

    }

    /**
     * Asks user for systemId to delete.
     * This deletes related runtime, service and/or systemintegration also.
     */

    @Override
    public void deleteBusinessSystem(Long systemId){
        BusinessSystem businessSystem = businessSystemRepository.findById(systemId).get();
        if(businessSystem.getRuntime() != null){
            deleteRuntime(systemId);
        }else if(businessSystem.getSystemIntegration() != null){
            deleteSystemIntegration(systemId);
        }
        businessSystemRepository.deleteById(systemId);

    }

    /**
     * Asks user for systemId to remove and delete runtime from.
     * Business System remains.
     */

    @Override
    public void deleteRuntime(Long systemId) {
        BusinessSystem businessSystem = businessSystemRepository.findById(systemId).get();
        Runtime runtime = businessSystem.getRuntime();
        businessSystem.setRuntime(null);
        businessSystemRepository.save(businessSystem);
        runtimeRepository.delete(runtime);

    }

    /**
     * Asks user for systemId to remove and delete service from.
     * Business System remains.
     */

    @Override
    public void deleteService(Long systemId) {
        BusinessSystem businessSystem = businessSystemRepository.findById(systemId).get();
        shb.poc.systemmap.v1.Service service = businessSystem.getService();
        businessSystem.setService(null);
        businessSystemRepository.save(businessSystem);
        serviceRepository.delete(service);
    }

    /**
     * Asks user for systemId to remove and delete system integration from.
     * Business System remains.
     */

    @Override
    public void deleteSystemIntegration(Long systemId) {
        BusinessSystem businessSystem = businessSystemRepository.findById(systemId).get();
        SystemIntegration systemIntegration = businessSystem.getSystemIntegration();
        businessSystem.setSystemIntegration(null);
        businessSystemRepository.save(businessSystem);
        systemIntegrationRepository.delete(systemIntegration);
    }

    /**
     * lists all systems and related entities
     */
    @Override
    public void listBusinessSystems() {
        List<BusinessSystem> systems = businessSystemRepository.findAll();
        System.out.println("LIST OF BUSINESS SYSTEMS");
        for(BusinessSystem system : systems){
            System.out.println("System id: " + system.getSystemId());
            System.out.println("System name: " + system.getName());
            System.out.println("System description: " + system.getDescription());
            if(system.getRuntime() != null){
                System.out.println("Runtime name: " + system.getRuntime().getName());
                System.out.println("Runtime description: " + system.getRuntime().getDescription());
            }
            if(system.getService() != null){
                System.out.println("Service name: " + system.getService().getName());
                System.out.println("Service description: " + system.getService().getDescription());
            }
            if(system.getSystemIntegration() != null){
                System.out.println("System integration name: " + system.getSystemIntegration().getName());
                System.out.println("System integration description: " + system.getSystemIntegration().getDescription());
                System.out.println("System integration consumer service: " + system.getSystemIntegration().getConsumerService());
                System.out.println("System integration producer service: " + system.getSystemIntegration().getProducerService());
            }

            System.out.println();
        }
    }

    /**
     * Asks user for systemId to make update to.
     * Also asks what to update (name/description) and what to update to.
     * Business System is saved to database.
     */

    @Override
    public void updateBusinessSystems(Long systemId, String updateItem, String change) {
        BusinessSystem businessSystem = businessSystemRepository.findById(systemId).get();
        if(updateItem.toLowerCase().equals("name")){
            businessSystem.setName(change);
        }else if(updateItem.toLowerCase().equals("description")){
            businessSystem.setDescription(change);
        }
        businessSystemRepository.save(businessSystem);
    }
}

