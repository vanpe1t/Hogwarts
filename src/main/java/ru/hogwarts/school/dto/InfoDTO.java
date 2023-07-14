package ru.hogwarts.school.dto;

import java.util.Objects;

public class InfoDTO {

    private String appName;
    private String appVersion;
    private String appEnvironment;

    public InfoDTO() {
    }

    public InfoDTO(String appName, String appVersion, String appEnvironment) {
        this.appName = appName;
        this.appVersion = appVersion;
        this.appEnvironment = appEnvironment;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppEnvironment() {
        return appEnvironment;
    }

    public void setAppEnvironment(String appEnvironment) {
        this.appEnvironment = appEnvironment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoDTO info = (InfoDTO) o;
        return Objects.equals(appName, info.appName) && Objects.equals(appVersion, info.appVersion) && Objects.equals(appEnvironment, info.appEnvironment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appName, appVersion, appEnvironment);
    }

    @Override
    public String toString() {
        return "Info{" +
                "appName='" + appName + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", appEnvironment='" + appEnvironment + '\'' +
                '}';
    }


}
