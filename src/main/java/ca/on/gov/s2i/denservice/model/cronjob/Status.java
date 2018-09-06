package ca.on.gov.s2i.denservice.model.cronjob;

public class Status {
    private String lastScheduleTime;

    public String getLastScheduleTime() {
        return lastScheduleTime;
    }

    public void setLastScheduleTime(String lastScheduleTime) {
        this.lastScheduleTime = lastScheduleTime;
    }

    @Override
    public String toString() {
        return "Status [lastScheduleTime=" + lastScheduleTime + "]";
    }
    
}
