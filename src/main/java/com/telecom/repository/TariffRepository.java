package com.telecom.repository;

import com.telecom.model.Tariff;
import java.util.List;

public interface TariffRepository {
    List<Tariff> findAll();
}