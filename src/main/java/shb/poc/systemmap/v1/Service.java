package shb.poc.systemmap.v1;

import javax.persistence.*;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SERVICEID")
    private Long serviceId;
    @Column (name="NAME")
    private String name;
    @Column (name="DESCRIPTION")
    String description;
    @OneToOne(mappedBy = "service")
    private BusinessSystem businessSystem;

    public Service() {
    }

    public Service(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
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
}
