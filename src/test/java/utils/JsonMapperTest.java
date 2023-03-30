package utils;

import model.Pokemon;
import org.junit.jupiter.api.Test;

import javax.json.JsonArray;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JsonMapperTest {
    @Test
    void shouldMapListToJsonArray() {
        Pokemon bulbasaur = Pokemon.builder()
                .id(1)
                .name("Bulbasaur")
                .sku("1")
                .price("5")
                .image_url("some_url")
                .build();

        Pokemon ivysaur = Pokemon.builder()
                .id(2)
                .name("Ivysaur")
                .sku("2")
                .price("10")
                .image_url("some_url1")
                .build();

        List<Pokemon> list = List.of(bulbasaur, ivysaur);

        JsonArray jsonArray = JsonMapper.createJsonArrayFromList(list);

        assertEquals("{\"id\":1,\"name\":\"Bulbasaur\"," +
                "\"price\":\"5\",\"sku\":\"1\",\"image_url\":\"some_url\"}", jsonArray.get(0).toString());

        assertEquals("{\"id\":2,\"name\":\"Ivysaur\"," +
                "\"price\":\"10\",\"sku\":\"2\",\"image_url\":\"some_url1\"}", jsonArray.get(1).toString());
    }

    @Test
    void shouldThrowNPEException() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            JsonMapper.createJsonArrayFromList(null);
        });

        assertEquals("list is marked non-null but is null", exception.getMessage());
    }

    @Test
    void shouldReturnEmptyList() {
        JsonArray jsonArray = JsonMapper.createJsonArrayFromList(Collections.EMPTY_LIST);
        assertEquals(0, jsonArray.size());
    }


}