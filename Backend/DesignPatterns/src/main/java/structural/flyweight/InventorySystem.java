package structural.flyweight;

import java.util.ArrayList;
import java.util.List;

public class InventorySystem {

    private final Catalog catalog = new Catalog();
    private final List<Order> orders = new ArrayList<>();

    void takeOrder(String itemName, int orderNumber) {
        Item item = catalog.lookup(itemName);
        Order order = new Order(orderNumber, item);
        orders.add(order);
    }

    void process() {
        orders.forEach(Order::processOrder);
        orders.clear();
    }

    String report() {
        return "\nTotal Item objects made: " + catalog.totalItemsMade();
    }
}
