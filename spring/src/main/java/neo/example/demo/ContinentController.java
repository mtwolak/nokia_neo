package neo.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static neo.example.demo.Utils.isEmptyList;

@RestController("continent")
public class ContinentController {

    @Autowired
    private ContinentRepository continentRepository;

    @PostMapping(value = "continent")
    public ResponseEntity<Continent> save(@RequestParam String continentName) {
        Continent saved = continentRepository.save(new Continent(continentName));
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping(value = "continent")
    public ResponseEntity<String> delete(@RequestParam(required = false) List<String> continentNames) {
        if (isEmptyList(continentNames)) {
            continentRepository.deleteAll();
        } else {
            List<Continent> continents = continentNames.stream().map(Continent::new).collect(Collectors.toList());
            continentRepository.deleteAll(continents);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "continent", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Continent> getAllContinents() {
        return continentRepository.findAll();
    }
}
