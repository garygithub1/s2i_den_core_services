package ca.on.gov.s2i.denservice.model.template;

public class Metadata {
    private Labels labels;

    public Labels getLabels() {
        return labels;
    }

    public void setLabels(Labels labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "Metadata [labels=" + labels + "]";
    }
    
    
}
