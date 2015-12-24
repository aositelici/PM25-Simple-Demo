package com.thoughtworks.myapplication.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PM25 implements Serializable{
    @SerializedName("position_name")
    private String positionName;

    @SerializedName("quality")
    private String quality;

    @SerializedName("aqi")
    private String aqi;

    @SerializedName("pm2_5")
    private String pm25;

    @SerializedName("time_point")
    private String timePOint;

    @SerializedName("primary_pollutant")
    private String primaryPollutant;


    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getQuality() {

        return quality;
    }

    public void setQuality(String quality) {

        this.quality = quality;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getTimePOint() {
        return timePOint;
    }

    public void setTimePOint(String timePOint) {
        this.timePOint = timePOint;
    }

    public String getPrimaryPollutant() {
        return primaryPollutant;
    }

    public void setPrimaryPollutant(String primaryPollutant) {
        this.primaryPollutant = primaryPollutant;
    }


}
