package neo.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integration-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CityControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldAddCityWhenCountryAndContinentDidNotExistBefore() throws Exception {
        mvc.perform(post("/city?continentName=Europe&countryName=Poland&cityName=Wroclaw"))
                .andExpect(status().isOk());

        mvc.perform(get("/continent").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].continentName").value("Europe"));

        mvc.perform(get("/country-by-continent-or-country-name").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].countryName").value("Poland"));

        mvc.perform(get("/city").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cityName").value("Wroclaw"));
    }

    @Test
    public void shouldFindCityByContinentName() throws Exception {
        mvc.perform(post("/city?continentName=Europe&countryName=Poland&cityName=Wroclaw"))
                .andExpect(status().isOk());
        mvc.perform(post("/city?continentName=Europe&countryName=Germany&cityName=Berlin"))
                .andExpect(status().isOk());
        //different continent, should not be found
        mvc.perform(post("/city?continentName=NA&countryName=USA&cityName=LA"))
                .andExpect(status().isOk());

        mvc.perform(get("/city?continentName=Europe"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].continentName").value("Europe"))
                .andExpect(jsonPath("$[0].countryName").value("Poland"))
                .andExpect(jsonPath("$[0].cityName").value("Wroclaw"))
                .andExpect(jsonPath("$[1].continentName").value("Europe"))
                .andExpect(jsonPath("$[1].countryName").value("Germany"))
                .andExpect(jsonPath("$[1].cityName").value("Berlin"))
                .andExpect(jsonPath("$[2].continentName").doesNotExist());
    }

    @Test
    public void shouldFindCityByCountryName() throws Exception {
        mvc.perform(post("/city?continentName=Europe&countryName=Poland&cityName=Wroclaw"))
                .andExpect(status().isOk());
        mvc.perform(post("/city?continentName=Europe&countryName=Poland&cityName=Gdansk"))
                .andExpect(status().isOk());
        //different country, should not be found
        mvc.perform(post("/city?continentName=Europe&countryName=Germany&cityName=Berlin"))
                .andExpect(status().isOk());

        mvc.perform(get("/city?countryName=Poland"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].continentName").value("Europe"))
                .andExpect(jsonPath("$[0].countryName").value("Poland"))
                .andExpect(jsonPath("$[0].cityName").value("Wroclaw"))
                .andExpect(jsonPath("$[1].continentName").value("Europe"))
                .andExpect(jsonPath("$[1].countryName").value("Poland"))
                .andExpect(jsonPath("$[1].cityName").value("Gdansk"))
                .andExpect(jsonPath("$[2].continentName").doesNotExist());
    }

    @Test
    public void shouldFindCitiesByContinentsAndCountries() throws Exception {
        mvc.perform(post("/city?continentName=Europe&countryName=Poland&cityName=Wroclaw"))
                .andExpect(status().isOk());
        mvc.perform(post("/city?continentName=Europe&countryName=Poland&cityName=Gdansk"))
                .andExpect(status().isOk());
        //different country, should not be found
        mvc.perform(post("/city?continentName=Europe&countryName=Germany&cityName=Berlin"))
                .andExpect(status().isOk());
        //different continent, should not be found
        mvc.perform(post("/city?continentName=NA&countryName=USA&cityName=LA"))
                .andExpect(status().isOk());

        mvc.perform(get("/city?continentName=Europe&countryName=Poland"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].continentName").value("Europe"))
                .andExpect(jsonPath("$[0].countryName").value("Poland"))
                .andExpect(jsonPath("$[0].cityName").value("Wroclaw"))
                .andExpect(jsonPath("$[1].continentName").value("Europe"))
                .andExpect(jsonPath("$[1].countryName").value("Poland"))
                .andExpect(jsonPath("$[1].cityName").value("Gdansk"))
                .andExpect(jsonPath("$[2].continentName").doesNotExist());
    }
}