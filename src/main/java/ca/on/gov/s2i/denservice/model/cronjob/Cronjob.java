package ca.on.gov.s2i.denservice.model.cronjob;

public class Cronjob {
    private Metadata metadata;
    private Spec spec;
    private Status status;
    
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
    
    @Override
    public String toString() {
        return "Cronjob [metadata=" + metadata + ", spec=" + spec + ", status=" + status + "]";
    }
}
