package ca.on.gov.s2i.denservice.model.template;

public class Labels {
    private String parent;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Labels [parent=" + parent + "]";
    }
    
    
}
