package ca.on.gov.s2i.denservice.model.jobtemplate;

public class Metadata {
    private String creationTimestamp;

    public String getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(String creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    @Override
    public String toString() {
        return "Metadata [creationTimestamp=" + creationTimestamp + "]";
    }
}
