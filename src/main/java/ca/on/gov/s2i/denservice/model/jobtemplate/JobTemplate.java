package ca.on.gov.s2i.denservice.model.jobtemplate;

public class JobTemplate {
    private Metadata metadata;
    private Spec spec;
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
        return "JobTemplate [metadata=" + metadata + ", spec=" + spec + "]";
    }
}
