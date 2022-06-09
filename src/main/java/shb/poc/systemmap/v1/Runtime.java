package shb.poc.systemmap.v1;

import javax.persistence.*;

@Entity
public class Runtime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RUNTIMEID")
    private Long runtimeId;
    @Column (name="NAME")
    private String name;
    @Column (name="DESCRIPTION")
    private String description;
    @OneToOne(mappedBy = "runtime")
    private BusinessSystem businessSystem;


    public Runtime() {
    }

    public Runtime(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getRuntimeId() {
        return runtimeId;
    }

    public void setRuntimeId(Long runtimeId) {
        this.runtimeId = runtimeId;
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

