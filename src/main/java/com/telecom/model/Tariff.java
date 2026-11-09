package com.telecom.model;

import java.util.Objects;

public abstract class Tariff {
    private final String name;
    private final double subscriptionFee; // або BigDecimal для фінансових розрахунків
    private final int clientsCount;

    protected Tariff(String name, double subscriptionFee, int clientsCount) {
        if (subscriptionFee < 0) {
            throw new IllegalArgumentException("Subscription fee cannot be negative");
        }
        if (clientsCount < 0) {
            throw new IllegalArgumentException("Clients count cannot be negative");
        }
        this.name = name;
        this.subscriptionFee = subscriptionFee;
        this.clientsCount = clientsCount;
    }

    public String getName() {
        return name;
    }

    public double getSubscriptionFee() {
        return subscriptionFee;
    }

    public int getClientsCount() {
        return clientsCount;
    }


    public abstract String getTariffDetails();

    @Override
    public String toString() {
        return String.format("%s (Fee: %.2f UAH, Clients: %d) - %s",
                name, subscriptionFee, clientsCount, getTariffDetails());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return Double.compare(tariff.subscriptionFee, subscriptionFee) == 0 &&
                clientsCount == tariff.clientsCount &&
                Objects.equals(name, tariff.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, subscriptionFee, clientsCount);
    }
}