package structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class Catalog {

    private Map<String, Item> items = new HashMap<>();

    public Item lookup(String itemName) {
        return items.computeIfAbsent(itemName, Item::new);
    }

    public int totalItemsMade() {
        return items.size();
    }
}
