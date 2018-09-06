package ca.on.gov.s2i.denservice.model.cronjob;

import java.util.Date;

public class SingleTask {
    private String cronjobId;
    private Date runningDate;
    public String getCronjobId() {
        return cronjobId;
    }
    public void setCronjobId(String cronjobId) {
        this.cronjobId = cronjobId;
    }
    public Date getRunningDate() {
        return runningDate;
    }
    public void setRunningDate(Date runningDate) {
        this.runningDate = runningDate;
    }
    
    @Override
    public String toString() {
        return "SingleTask [cronjobId=" + cronjobId + ", runningDate=" + runningDate + "]";
    }
}
