package ca.on.gov.s2i.denservice.model.jobtemplate;

import ca.on.gov.s2i.denservice.model.template.Template;

public class Spec {
    private Template template;

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    @Override
    public String toString() {
        return "Spec [template=" + template + "]";
    }
    
}
