package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.domain.SolarSystem;

import java.util.List;
@Repository
public interface SolarSystemRepository extends JpaRepository<SolarSystem, Integer> {
    @Procedure
    Integer get_solar_systems_number_with_filter(Double feedInTariff);

    @Procedure("get_solar_systems_number_with_filter")
    Integer getSolarSystemsNumberWithFilter(Double feedInTariff);

    @Query(value = "CALL get_solar_systems_with_tariff_bigger(:tariff_in)", nativeQuery = true)
    List<SolarSystem> findSolarSystemsWithTariffBigger(@Param("tariff_in") Double tariff);

    List<SolarSystem> findByEnergySold(Integer energySold);

    @Procedure("get_max_energy_sold")
    Integer getMaxEnergySold();
}