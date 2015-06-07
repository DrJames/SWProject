package com.swproject.fi.swproject;

import android.graphics.drawable.Drawable;

/**
 * Created by alex on 7.6.2015.
 */
public class Device {
    private Drawable icon;
    private String ipAddress;
    private String network;
    private String name;

    public Device(Drawable icon, String name, String ipAddress, String network){
        this.icon = icon;
        this.name = name;
        this.ipAddress = ipAddress;
        this.network = network;
    }

    public Drawable getIcon(){
        return icon;
    }

    public void setIcon(Drawable icon){
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

    public String getNetwork(){
        return network;
    }

    public void setNetwork(String network){
        this.network = network;
    }
}
