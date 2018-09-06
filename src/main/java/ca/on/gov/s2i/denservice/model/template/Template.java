package ca.on.gov.s2i.denservice.model.template;

import java.util.List;

public class Template {
    private Metadata metadata;
    private Spec spec;
    private List<Container> containers;
    public Metadata getMetadata() {
        return metadata;
    }
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
    public Spec getSpec() {
        return spec;
    }
    public void setSpec(Spec spec) {
        this.spec = spec;
    }
    public List<Container> getContainers() {
        return containers;
    }
    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }
    
    @Override
    public String toString() {
        return "Template [metadata=" + metadata + ", spec=" + spec + ", containers=" + containers + "]";
    }
    
    
}
