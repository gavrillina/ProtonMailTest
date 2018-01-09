package buissnes_object.android_objects;

public class MobileCapabilities {
    private String platformName;
    private String version;
    private String deviceName;
    private String appPackage;
    private String appActivity;


    public MobileCapabilities(String platformName, String version, String deviceName, String appPackage, String appActivity) {
        this.platformName = platformName;
        this.version = version;
        this.deviceName = deviceName;
        this.appPackage = appPackage;
        this.appActivity = appPackage;
    }

    public String getPlatformName() {
        return platformName;
    }
    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getDeviceName() {
        return deviceName;
    }
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    public String getAppPackage() {
        return appPackage;
    }
    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }
    public String getAppActivity() {
        return appActivity;
    }
    public void setAppActivity(String appActivity) {
        this.appActivity = appActivity;
    }

}