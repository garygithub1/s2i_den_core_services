package ca.on.gov.s2i.denservice.model.cronjobs;

public class Metadata {
    private String selfLink;
    private String resourceVersion;
    
    public String getSelfLink() {
        return selfLink;
    }
    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }
    public String getResourceVersion() {
        return resourceVersion;
    }
    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
    }
    
    @Override
    public String toString() {
        return "Metadata [selfLink=" + selfLink + ", resourceVersion=" + resourceVersion + "]";
    }
}
