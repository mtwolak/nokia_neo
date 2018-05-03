package neo.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, CityPk> {

    List<City> findCitiesByCountryName(String countryName);

    @Query("SELECT c FROM City c where c.country.continent = :continent")
    List<City> findCitiesByContinent(@Param("continent") Continent continent);

    @Query("SELECT c FROM City c where c.country.continent = :continent AND c.countryName = :countryName")
    List<City> findCitiesByCountryNameAndContinent(@Param("countryName") String countryName, @Param("continent") Continent continent);

    @Transactional
    void deleteAllByCountryName(String countryName);

    @Transactional
    void deleteAllByCityName(String cityName);
}
