package com.swproject.fi.swproject;

/**
 * Created by alex on 7.6.2015.
 */
public class Device {
    private Integer icon;
    private String ipAddress;
    private String macAddress;
    private String name;

    public Device(Integer icon, String name, String ipAddress, String macAddress){
        this.icon = icon;
        this.name = name;
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
    }

    public Integer getIcon(){
        return icon;
    }

    public void setIcon(Integer icon){
        this.icon = icon;
    }

    public String getIpAddress(){
        return ipAddress;
    }

    public void setIpAddress(String ipAddress){
        this.ipAddress = ipAddress;
    }

    public String getName (){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getMAC(){
        return macAddress;
    }

    public void setMAC(String network){
        this.macAddress = network;
    }
}
