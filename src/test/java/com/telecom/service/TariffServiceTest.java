package com.telecom.service;

import com.telecom.model.StandartTariff;
import com.telecom.model.Tariff;
import com.telecom.model.UnlimitedDataTariff;
import com.telecom.repository.TariffRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TariffServiceTest {

    @Mock
    private TariffRepository repository;

    @InjectMocks
    private TariffService tariffService;

    private List<Tariff> sampleTariffs;

    @BeforeEach
    void setUp() {
        sampleTariffs = List.of(
                new StandartTariff("Smart Light", 150.0, 1200, 300, 10),
                new UnlimitedDataTariff("Max Power", 300.0, 3500, 100),
                new StandartTariff("Medium Pro", 220.0, 800, 600, 25)
        );
    }

    @Test
    void shouldCalculateTotalClientsCorrectly() {
        when(repository.findAll()).thenReturn(sampleTariffs);

        int total = tariffService.calculateTotalClients();

        assertEquals(5500, total);
    }

    @Test
    void shouldSortTariffsByFeeAscending() {
        when(repository.findAll()).thenReturn(sampleTariffs);

        List<Tariff> sorted = tariffService.sortTariffsBySubscriptionFee();

        assertEquals("Smart Light", sorted.get(0).getName());
        assertEquals("Medium Pro", sorted.get(1).getName());
        assertEquals("Max Power", sorted.get(2).getName());
    }

    @Test
    void shouldFindTariffsWithinGivenFeeRange() {
        when(repository.findAll()).thenReturn(sampleTariffs);

        List<Tariff> matched = tariffService.findTariffsByFeeRange(140.0, 250.0);

        assertEquals(2, matched.size());
        assertTrue(matched.stream().allMatch(t -> t.getSubscriptionFee() >= 140.0 && t.getSubscriptionFee() <= 250.0));
    }

    @Test
    void shouldThrowExceptionWhenMinFeeExceedsMaxFee() {
        assertThrows(IllegalArgumentException.class, () ->
                tariffService.findTariffsByFeeRange(200.0, 100.0)
        );
    }
}