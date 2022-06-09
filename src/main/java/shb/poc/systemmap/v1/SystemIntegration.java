package shb.poc.systemmap.v1;

import javax.persistence.*;

@Entity
public class SystemIntegration{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SYSTEMINTEGRATIONID")
    private Long systemIntegrationId;
    @Column (name="NAME")
    private String name;
    @Column (name="DESCRIPTION")
    private String description;
    @Column (name="CONSUMERSERVICE")
    private Long consumerService;
    @Column (name="PRODUCERSERVICE")
    private Long producerService;
    @OneToOne(mappedBy = "systemIntegration")
    private BusinessSystem businessSystem;

    public SystemIntegration() {
    }

    public SystemIntegration(String name, String description, Long consumerService, Long producerService) {
        this.name = name;
        this.description = description;
        this.consumerService = consumerService;
        this.producerService = producerService;
    }

    public Long getSystemIntegrationId() {
        return systemIntegrationId;
    }

    public void setSystemIntegrationId(Long systemIntegrationId) {
        this.systemIntegrationId = systemIntegrationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getConsumerService() {
        return consumerService;
    }

    public void setConsumerService(Long consumerService) {
        this.consumerService = consumerService;
    }

    public Long getProducerService() {
        return producerService;
    }

    public void setProducerService(Long producerService) {
        this.producerService = producerService;
    }
}
