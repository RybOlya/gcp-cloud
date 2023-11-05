package ua.lviv.iot.repository;

import ua.lviv.iot.domain.SolarBattery;
import ua.lviv.iot.domain.SolarPanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolarPanelRepository extends JpaRepository<SolarPanel, Integer> {
    List<SolarPanel> findByIpAddress(String ip);
}
