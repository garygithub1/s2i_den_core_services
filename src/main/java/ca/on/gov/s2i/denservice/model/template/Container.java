package ca.on.gov.s2i.denservice.model.template;

import java.util.List;

public class Container {
    private String name;
    private String image;
    private List<String> command;
    private String terminationMessagePath;
    private String terminationMessagePolicy;
    private String imagePullPolicy;
    //private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public List<String> getCommand() {
        return command;
    }
    public void setCommand(List<String> command) {
        this.command = command;
    }
    public String getTerminationMessagePath() {
        return terminationMessagePath;
    }
    public void setTerminationMessagePath(String terminationMessagePath) {
        this.terminationMessagePath = terminationMessagePath;
    }
    public String getTerminationMessagePolicy() {
        return terminationMessagePolicy;
    }
    public void setTerminationMessagePolicy(String terminationMessagePolicy) {
        this.terminationMessagePolicy = terminationMessagePolicy;
    }
    public String getImagePullPolicy() {
        return imagePullPolicy;
    }
    public void setImagePullPolicy(String imagePullPolicy) {
        this.imagePullPolicy = imagePullPolicy;
    }
    
    @Override
    public String toString() {
        return "Container [name=" + name + ", image=" + image + ", command=" + command + ", terminationMessagePath="
                + terminationMessagePath + ", terminationMessagePolicy=" + terminationMessagePolicy
                + ", imagePullPolicy=" + imagePullPolicy + "]";
    }
}
