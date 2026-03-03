package com.example.familycard;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class FamilyCardApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads(){
        assertThat(this.restTemplate).isNotNull();
    }

    @Test
    void shouldReturnCashCardWhenDataIsSaved(){
        ResponseEntity<String> response = this.restTemplate.getForEntity("/cashcards/99", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isNotNull();
        assertThat(id).isEqualTo(99);

        Double amount = documentContext.read("$.amount");
        assertThat(amount).isNotNull();
        assertThat(amount).isNotNegative();
        assertThat(amount).isEqualTo(123.45);
    }

    @Test
    void shouldReturnACashCardWithUnknowId(){
        ResponseEntity<String> response = this.restTemplate.getForEntity("/cashcards/1000", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }

    @Test
    void shouldCreateANewCashCard(){
        CashCard newCashCard = new CashCard(null, 250.0);
        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/cashcards", newCashCard, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // send a 201 (Created) response containing a Location header field
        // that provides an identifier for the primary resource created
        // https://www.rfc-editor.org/rfc/rfc9110#section-10.2.2
        URI locationOfNewCashCard = createResponse.getHeaders().getLocation();
        assertThat(locationOfNewCashCard).isNotNull();
        System.out.println("\n\n\nthe location: "+locationOfNewCashCard);

        // Finally, we'll use the Location header's information to fetch the newly created CashCard.
        ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewCashCard, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Number id = documentContext.read("$.id");
        Double amount = documentContext.read("$.amount");

        assertThat(id).isNotNull();
        assertThat(amount).isEqualTo(250.0);
    }
}


