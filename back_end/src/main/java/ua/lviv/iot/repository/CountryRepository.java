package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.domain.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Procedure("create_tables_by_country_names")
    void createTablesByCountryNames();
}