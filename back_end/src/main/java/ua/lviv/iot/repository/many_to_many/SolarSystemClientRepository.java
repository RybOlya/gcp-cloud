package ua.lviv.iot.repository.many_to_many;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.domain.many_to_many.SolarSystemClient;

import java.util.List;

@Repository
public interface SolarSystemClientRepository extends JpaRepository<SolarSystemClient, Integer> {
    @Query(value = "call insertInSolarSystemClient(:solar_system_id_in, :client_id_in)",
            nativeQuery =
            true)
    SolarSystemClient insertInSolarSystemClient(
            @Param("solar_system_id_in") int solarSystemIdIn,
            @Param("client_id_in") int clientIdIn);

    @Query(value = "call updateSolarSystemClient(:id, :solar_system_id_in, :client_id_in)",
            nativeQuery =
            true)
    SolarSystemClient updateSolarSystemClient(
            @Param("id") int id,
            @Param("solar_system_id_in") int solarSystemIdIn,
            @Param("client_id_in") int clientIdIn);
    @Procedure("insert_into_solar_system_client")
    Integer insertIntoSolarSystemClient(Integer solarSystemId, String clientSurname);

    List<SolarSystemClient> findSolarSystemClientBySolarSystemId(int solarSystemIdIn);
    List<SolarSystemClient> findSolarSystemClientByClientId(int clientIdIn);

}
