package dev.kacperm.smartcity;

import dev.kacperm.smartcity.dao.Citizen;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class SmartCityApplicationTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void shouldReturnCitizen() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/citizen/find/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldNotReturnCitizen() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/citizen/find/9999", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldCitizenBeCreated() {
        Citizen testCitizen = new Citizen(null, "test@gmail.com", null, null, null, null, 0);
        ResponseEntity<String> response = testRestTemplate.postForEntity("/citizen/create", testCitizen, String.class);

        System.out.println("status " + response.getStatusCode());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
