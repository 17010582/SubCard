package sg.com.subcard_17010582.subcard;

import java.io.Serializable;

public class card implements Serializable {

    private int id;
    private String appName;
    private double limit;
    private boolean active;

    public card() {
    }

    public card(int id, String appName, double limit) {
        this.id = id;
        this.appName = appName;
        this.limit = limit;
        this.active = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public double getLimit() {
        return limit;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
