import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import model.Pokemon;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.JsonMapper;

import javax.json.JsonArray;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    private final static List<Pokemon> pokemonList = new LinkedList<>();

    public static void main(String[] args) {
        try {
            var list = getPokemonListFromWebSite();

            fillListWithPokemons(list);

            printFormattingJson();

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static Elements getPokemonListFromWebSite() throws IOException {
        var document = Jsoup.connect("https://scrapeme.live/shop/").get();
        return document.getElementsByClass("product_cat-pokemon");
    }

    private static Pokemon createPokemonObject(Element element) {
        int pokemonId = Integer.parseInt(element.getElementsByClass("button").attr("data-product_id"));

        return Pokemon.builder()
                .id(pokemonId)
                .sku(element.getElementsByClass("button").attr("data-product_sku"))
                .name(element.getElementsByClass("woocommerce-loop-product__title").text())
                .price(element.getElementsByClass("woocommerce-Price-amount amount").text())
                .image_url(element.getElementsByTag("img").attr("src"))
                .build();
    }

    private static void fillListWithPokemons(List<Element> list) {
        for (var element : list) {
            pokemonList.add(createPokemonObject(element));
        }
    }

    private static void printFormattingJson() {
        String array = JsonMapper.createJsonArrayFromList(pokemonList).toString();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(array);

        System.out.println(gson.toJson(je));
    }
}
