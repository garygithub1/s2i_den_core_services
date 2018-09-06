package ca.on.gov.s2i.denservice.model.cronjob;

import ca.on.gov.s2i.denservice.model.jobtemplate.JobTemplate;

public class Spec {
    private String schedule;
    private String concurrencyPolicy;
    private String successfulJobsHistoryLimit;
    private String failedJobsHistoryLimit;
    private JobTemplate JobTemplate;
    public String getSchedule() {
        return schedule;
    }
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
    public String getConcurrencyPolicy() {
        return concurrencyPolicy;
    }
    public void setConcurrencyPolicy(String concurrencyPolicy) {
        this.concurrencyPolicy = concurrencyPolicy;
    }
    public String getSuccessfulJobsHistoryLimit() {
        return successfulJobsHistoryLimit;
    }
    public void setSuccessfulJobsHistoryLimit(String successfulJobsHistoryLimit) {
        this.successfulJobsHistoryLimit = successfulJobsHistoryLimit;
    }
    public String getFailedJobsHistoryLimit() {
        return failedJobsHistoryLimit;
    }
    public void setFailedJobsHistoryLimit(String failedJobsHistoryLimit) {
        this.failedJobsHistoryLimit = failedJobsHistoryLimit;
    }
    public JobTemplate getJobTemplate() {
        return JobTemplate;
    }
    public void setJobTemplate(JobTemplate jobTemplate) {
        JobTemplate = jobTemplate;
    }
    
    @Override
    public String toString() {
        return "Spec [schedule=" + schedule + ", concurrencyPolicy=" + concurrencyPolicy
                + ", successfulJobsHistoryLimit=" + successfulJobsHistoryLimit + ", failedJobsHistoryLimit="
                + failedJobsHistoryLimit + ", JobTemplate=" + JobTemplate + "]";
    }
    
}
