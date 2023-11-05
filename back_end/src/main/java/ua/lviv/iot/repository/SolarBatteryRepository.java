package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.domain.SolarBattery;

import java.util.List;
@Repository
public interface SolarBatteryRepository extends JpaRepository<SolarBattery, Integer> {
    SolarBattery findByModel(String model);

}