package shb.poc.systemmap.v1;


import javax.persistence.*;


@Entity
public class BusinessSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="SYSTEMID")
    Long systemId;
    @Column (name="NAME")
    String name;
    @Column (name="DESCRIPTION")
    String description;
    @Column (name="DESCRIPTIONURL")
    String documentationUrl;
    @OneToOne(cascade = CascadeType.REMOVE) //one runtime for each business system
    @JoinTable(name = "bus_runtime",
            joinColumns =
                    { @JoinColumn(name = "system_id", referencedColumnName = "SYSTEMID") },
            inverseJoinColumns =
                    { @JoinColumn(name = "runtime_id", referencedColumnName = "RUNTIMEID") })
    private Runtime runtime;

    @OneToOne(cascade = CascadeType.REMOVE) //one service for each business system
    @JoinTable(name = "bus_service",
            joinColumns =
                    { @JoinColumn(name = "system_id", referencedColumnName = "SYSTEMID") },
            inverseJoinColumns =
                    { @JoinColumn(name = "service_id", referencedColumnName = "SERVICEID") })
    private Service service;

    @OneToOne(cascade = CascadeType.REMOVE) //one system integration for each business system
    @JoinTable(name = "bus_sysint",
            joinColumns =
                    { @JoinColumn(name = "system_id", referencedColumnName = "SYSTEMID") },
            inverseJoinColumns =
                    { @JoinColumn(name = "systemintegration_id", referencedColumnName = "SYSTEMINTEGRATIONID") })
    SystemIntegration systemIntegration;

    //Empty constructor required of classes annotated with @Entity
    public BusinessSystem() {
    }

    public BusinessSystem(String description, String documentationUrl, String name) {
        this.description = description;
        this.documentationUrl = documentationUrl;
        this.name = name;

    }

    //runtime, service and systemintegration will be added to business system separately

    public BusinessSystem(Runtime runtime) {
        this.runtime = runtime;
    }

    public BusinessSystem(Service service) {
        this.service = service;
    }

    public BusinessSystem(SystemIntegration systemIntegration) {
        this.systemIntegration = systemIntegration;
    }

    public Runtime getRuntime() {
        return runtime;
    }

    public void setRuntime(Runtime runtime) {
        this.runtime = runtime;
    }


    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public SystemIntegration getSystemIntegration() {
        return systemIntegration;
    }

    public void setSystemIntegration(SystemIntegration systemIntegration) {
        this.systemIntegration = systemIntegration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocumentationUrl() {
        return documentationUrl;
    }

    public void setDocumentationUrl(String documentationUrl) {
        this.documentationUrl = documentationUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSystemId() {
        return systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }
}

