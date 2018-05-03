package neo.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    List<Country> findCountriesByContinent(Continent continent);

    @Transactional
    void deleteAllByContinent(Continent continentName);

    @Transactional
    void deleteAllByCountryName(String countryName);

    @Transactional
    void deleteAllByCountryNameAndContinent(String countryName, Continent continent);

    List<Country> findCountriesByCountryName(String countryName);

    List<Country> findCountriesByCountryNameAndContinent(String countryName, Continent continent);

    @Query("SELECT c FROM Country c JOIN c.city cities where :cityName = cities.cityName")
    List<Country> getCountriesByCity(@Param("cityName") String cityName);
}
