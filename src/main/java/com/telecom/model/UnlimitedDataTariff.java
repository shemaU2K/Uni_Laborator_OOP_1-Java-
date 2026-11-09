package com.telecom.model;

public class UnlimitedDataTariff extends Tariff {
    private final int speedLimitMbps;

    public UnlimitedDataTariff(String name, double subscriptionFee, int clientsCount, int speedLimitMbps) {
        super(name, subscriptionFee, clientsCount);
        this.speedLimitMbps = speedLimitMbps;
    }

    public int getSpeedLimitMbps() {
        return speedLimitMbps;
    }

    @Override
    public String getTariffDetails() {
        return String.format("Data: Unlimited (Speed limit: %d Mbps)", speedLimitMbps);
    }
}