package model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Pokemon {
    int id;
    String name;
    String price;
    String sku;
    String image_url;
}
