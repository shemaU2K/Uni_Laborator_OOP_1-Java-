package com.telecom.service;

import com.telecom.model.Tariff;
import com.telecom.repository.TariffRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TariffService {
    private final TariffRepository repository;

    public TariffService(TariffRepository repository) {
        this.repository = repository;
    }

    public int calculateTotalClients() {
        return repository.findAll().stream()
                .mapToInt(Tariff::getClientsCount)
                .sum();
    }

    public List<Tariff> sortTariffsBySubscriptionFee() {
        return repository.findAll().stream()
                .sorted(Comparator.comparingDouble(Tariff::getSubscriptionFee))
                .collect(Collectors.toList());
    }

    public List<Tariff> findTariffsByFeeRange(double minFee, double maxFee) {
        if (minFee > maxFee) {
            throw new IllegalArgumentException("minFee cannot be greater than maxFee");
        }
        return repository.findAll().stream()
                .filter(t -> t.getSubscriptionFee() >= minFee && t.getSubscriptionFee() <= maxFee)
                .collect(Collectors.toList());
    }
}