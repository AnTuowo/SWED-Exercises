package com.websitemonitor.domain;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Subscription {
    private final String subscriptionId;
    private final String url;
    private String frequency;
    private String channel;
    private Date lastChecked;

    public Subscription(String subscriptionId, String url,
            String frequency, String channel) {
        this.subscriptionId = subscriptionId;
        this.url = url;
        this.frequency = frequency;
        this.channel = channel;
        this.lastChecked = new Date(0);
    }

    public String getSubscriptionId() { return subscriptionId; }
    public String getUrl() { return url; }
    public String getFrequency() { return frequency; }
    public String getChannel() { return channel; }
    public Date getLastChecked() { return lastChecked; }

    public void setFrequency(String frequency) { this.frequency = frequency; }
    public void setChannel(String channel) { this.channel = channel; }
    public void updateLastChecked(Date date) { this.lastChecked = date; }

    public long getFrequencyMillis() {
        String normalized = frequency == null ? "" : frequency.trim().toLowerCase();

        switch (normalized) {
            case "1 minute" -> {
                return 60 * 1000L;
            }
            case "5 minute" -> {
                return 5 * 60 * 1000L;
            }
            case "hourly" -> {
                return 60 * 60 * 1000L;
            }
            case "daily" -> {
                return 24 * 60 * 60 * 1000L;
            }
            case "weekly" -> {
                return 7 * 24 * 60 * 60 * 1000L;
            }
        }

        Matcher matcher = Pattern.compile("^every\\s+(\\d+)\\s+minute(?:s)?$").matcher(normalized);
        if (matcher.matches()) {
            return Long.parseLong(matcher.group(1)) * 60 * 1000L;
        }

        return 60 * 60 * 1000L;
    }
}