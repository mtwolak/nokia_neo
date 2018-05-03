package neo.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class CountryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldAddCountryWhenContinentDidNotExistBefore() throws Exception {
        mvc.perform(post("/country?continentName=Europe&countryName=Poland"))
                .andExpect(status().isOk());

        mvc.perform(get("/continent").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].continentName").value("Europe"));
    }

    @Test
    public void shouldFindCountriesByContinent() throws Exception {
        mvc.perform(post("/city?continentName=Europe&countryName=Poland&cityName=Wroclaw"))
                .andExpect(status().isOk());
        mvc.perform(post("/city?continentName=Europe&countryName=Poland&cityName=Gdansk"))
                .andExpect(status().isOk());
        mvc.perform(post("/city?continentName=Europe&countryName=Germany&cityName=Berlin"))
                .andExpect(status().isOk());
        mvc.perform(post("/city?continentName=NA&countryName=USA&cityName=LA"))
                .andExpect(status().isOk());

        mvc.perform(get("/country-by-continent-or-country-name?continentName=Europe").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].countryName").value("Poland"))
                .andExpect(jsonPath("$[0].continentName").value("Europe"))
                .andExpect(jsonPath("$[1].countryName").value("Germany"))
                .andExpect(jsonPath("$[1].continentName").value("Europe"))
                .andExpect(jsonPath("$[2].countryName").doesNotExist());
    }

    @Test
    public void shouldFindCountryByCities() throws Exception {
        mvc.perform(post("/city?continentName=Europe&countryName=Poland&cityName=Wroclaw"))
                .andExpect(status().isOk());
        mvc.perform(post("/city?continentName=Europe&countryName=Poland&cityName=Gdansk"))
                .andExpect(status().isOk());
        mvc.perform(post("/city?continentName=Europe&countryName=Germany&cityName=Berlin"))
                .andExpect(status().isOk());

        mvc.perform(get("/country-by-city-name?cityName=Wroclaw").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].countryName").value("Poland"))
                .andExpect(jsonPath("$[1].countryName").doesNotExist());

    }
}