package ca.on.gov.s2i.denservice.model.cronjobs;

import java.util.List;

import ca.on.gov.s2i.denservice.model.cronjob.Cronjob;

public class Cronjobs {
    private String kind;
    private String apiVersion;
    private Metadata metadata;
    private List<Cronjob> items;
    public String getKind() {
        return kind;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }
    public String getApiVersion() {
        return apiVersion;
    }
    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }
    public Metadata getMetadata() {
        return metadata;
    }
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
    public List<Cronjob> getItems() {
        return items;
    }
    public void setItems(List<Cronjob> items) {
        this.items = items;
    }
    
    @Override
    public String toString() {
        return "Cronjobs [kind=" + kind + ", apiVersion=" + apiVersion + ", metadata=" + metadata + ", items=" + items
                + "]";
    }
}
