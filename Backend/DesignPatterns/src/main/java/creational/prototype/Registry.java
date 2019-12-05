package creational.prototype;

import java.util.HashMap;
import java.util.Map;

public class Registry {

    private Map<String, Item> items = new HashMap<>();

    public Registry() {
        loadItems();
    }

    private void loadItems() {

        Movie movie = new Movie();
        movie.setTitle("Movie 1");
        movie.setPrice(22);
        movie.setUrl("wwww.google.com.br");
        movie.setRuntime("2 hours");
        items.put("Movie", movie);

        Book book = new Book();
        book.setTitle("Book 1");
        book.setPrice(10);
        book.setUrl("www.google.com.br");
        book.setNumberOfPages(100);
        items.put("Book", book);
    }

    public Item createItem(String type) {
        Item item = null;


        try {
            item = (Item) items.get(type).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return item;
    }
}
