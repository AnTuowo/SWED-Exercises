package com.websitemonitor.domain;

import java.util.Date;

public class Subscription {
    private String subscriptionId;
    private String url;
    private String frequency;
    private String channel;
    private Date   lastChecked;

    public Subscription(String subscriptionId, String url,
                        String frequency, String channel) {
        this.subscriptionId = subscriptionId;
        this.url       = url;
        this.frequency = frequency;
        this.channel   = channel;
        this.lastChecked = new Date();
    }

    public String getSubscriptionId() { return subscriptionId; }
    public String getUrl()            { return url; }
    public String getFrequency()      { return frequency; }
    public String getChannel()        { return channel; }
    public Date   getLastChecked()    { return lastChecked; }

    public void setFrequency(String frequency) { this.frequency = frequency; }
    public void setChannel(String channel)     { this.channel   = channel; }
    public void updateLastChecked(Date date)   { this.lastChecked = date; }
}