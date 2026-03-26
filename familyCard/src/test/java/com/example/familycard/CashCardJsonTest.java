package com.example.familycard;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@JsonTest // that mean this class uses the Jackson framework
public class CashCardJsonTest {

    @Autowired
    private JacksonTester<CashCard> json;
    @Autowired
    private JacksonTester<CashCard[]> jsonList;
    private final CashCard[] cashCards = Arrays.array(
            new CashCard(99L, 123.45, "sarah2"),
            new CashCard(100L, 1.00,"sarah1"),
            new CashCard(101L, 150.00, "sarah3"));


    @Test
    void cashCardSerializationTest() throws IOException {
        CashCard cashCard = new CashCard(99L, 123.45, "sarah1");
        assertThat(json.write(cashCard)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id").isEqualTo(99);
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount").isEqualTo(123.45);
    }

    @Test
    void cashCardDeserializationTest() throws IOException {
        String expected = """
                {
                    "id": 88,
                    "amount": 238.9,
                    "owner":"sarah1"
                }
                """;
        assertThat(json.parse(expected)).isEqualTo(new CashCard(88L, 238.9, "sarah1"));
        assertThat(json.parseObject(expected).id()).isEqualTo(88);
        assertThat(json.parseObject(expected).amount()).isEqualTo(238.9);
    }

    @Test
    void cashCardListSerializationTest() throws IOException {
        assertThat(jsonList.write(this.cashCards)).isStrictlyEqualToJson("list.json");
    }

    @Test
    void cashCardListDeserializationTest() throws IOException {
        String expected="""
         [
            { "id": 99, "amount": 123.45, "owner": "sarah2" },
            { "id": 100, "amount": 1.00, "owner": "sarah1" },
            { "id": 101, "amount": 150.00, "owner": "sarah3" }
         ]
         """;
        assertThat(jsonList.parse(expected)).isEqualTo(this.cashCards);
    }
}
