package neo.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("city")
public class CityController {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CountryRepository countryRepository;

    @PostMapping(value = "city")
    public ResponseEntity<City> addCities(@RequestParam String continentName, @RequestParam String countryName, @RequestParam String cityName) {
        City city = new City(cityName, new Country(countryName, new Continent(continentName)));
        City saved = cityRepository.save(city);
        return ResponseEntity.ok(saved);
    }

    @GetMapping(value = "city")
    public List<City> getCities(@RequestParam(required = false) String continentName, @RequestParam(required = false) String countryName) {
        if (continentName == null && countryName == null) {
            return cityRepository.findAll();
        } else if (continentName == null) {
            return cityRepository.findCitiesByCountryName(countryName);
        } else if (countryName == null) {
            return cityRepository.findCitiesByContinent(new Continent(continentName));
        } else {
            return cityRepository.findCitiesByCountryNameAndContinent(countryName, new Continent(continentName));
        }
    }

    @DeleteMapping(value = "city-continent")
    public ResponseEntity<String> deleteCitiesByContinent(@RequestParam String continentName) {
        List<City> citiesByContinent = cityRepository.findCitiesByContinent(new Continent(continentName));
        cityRepository.deleteAll(citiesByContinent);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "city-country")
    public ResponseEntity<String> deleteCitiesByCountry(@RequestParam String countryName) {
        cityRepository.deleteAllByCountryName(countryName);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "city-name")
    public ResponseEntity<String> deleteCitiesByCityName(@RequestParam String cityName) {
        cityRepository.deleteAllByCityName(cityName);
        return ResponseEntity.ok().build();
    }


}
