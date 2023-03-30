package utils;

import lombok.NonNull;
import model.Pokemon;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import java.util.List;

public class JsonMapper {
    public static JsonArray createJsonArrayFromList(@NonNull List<Pokemon> list) {
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();

        for (Pokemon pokemon : list) {
            jsonArray.add(Json.createObjectBuilder()
                    .add("id", pokemon.getId())
                    .add("name", pokemon.getName())
                    .add("price", pokemon.getPrice())
                    .add("sku", pokemon.getSku())
                    .add("image_url", pokemon.getImage_url()));
        }

        return jsonArray.build();
    }
}
