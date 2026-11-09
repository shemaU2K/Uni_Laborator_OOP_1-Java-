package com.telecom.model;

public class StandartTariff extends Tariff {
    private final int callMinutes;
    private final int gigabytes;

    public StandartTariff(String name, double subscriptionFee, int clientsCount, int callMinutes, int gigabytes) {
        super(name, subscriptionFee, clientsCount);
        this.callMinutes = callMinutes;
        this.gigabytes = gigabytes;
    }

    public int getCallMinutes() {
        return callMinutes;
    }

    public int getGigabytes() {
        return gigabytes;
    }

    @Override
    public String getTariffDetails() {
        return String.format("Minutes: %d, Data: %d GB", callMinutes, gigabytes);
    }
}