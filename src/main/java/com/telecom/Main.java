package com.telecom;

import com.telecom.model.StandartTariff;
import com.telecom.model.Tariff;
import com.telecom.model.UnlimitedDataTariff;
import com.telecom.repository.TariffRepository;
import com.telecom.service.TariffService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Проста in-memory реалізація репозиторію
        TariffRepository inMemoryRepo = () -> List.of(
                new StandartTariff("Basic 4G", 175.0, 4300, 500, 15),
                new UnlimitedDataTariff("GigaStream", 350.0, 9200, 1000),
                new StandartTariff("Light Voice", 100.0, 1500, 200, 5)
        );

        TariffService service = new TariffService(inMemoryRepo);

        System.out.println("=== Total Subscribers ===");
        System.out.println("Total: " + service.calculateTotalClients());

        System.out.println("\n=== Tariffs Sorted by Monthly Fee ===");
        service.sortTariffsBySubscriptionFee().forEach(System.out::println);

        System.out.println("\n=== Tariffs in range [150.0 - 400.0 UAH] ===");
        service.findTariffsByFeeRange(150.0, 400.0).forEach(System.out::println);
    }
}