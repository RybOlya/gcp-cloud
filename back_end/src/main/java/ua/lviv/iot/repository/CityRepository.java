package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.query.Procedure;
import ua.lviv.iot.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    @Procedure("create_tables_by_city_names")
    void createTablesByCityNames();
}
