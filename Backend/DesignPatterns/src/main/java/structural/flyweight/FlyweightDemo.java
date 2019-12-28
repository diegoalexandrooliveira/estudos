package structural.flyweight;

public class FlyweightDemo {


    public static void main(String[] args) {
        InventorySystem inventorySystem = new InventorySystem();

        inventorySystem.takeOrder("TV", 1);
        inventorySystem.takeOrder("Radio", 2);
        inventorySystem.takeOrder("Computer", 3);
        inventorySystem.takeOrder("Computer", 4);
        inventorySystem.takeOrder("TV", 5);
        inventorySystem.takeOrder("TV", 6);
        inventorySystem.takeOrder("Radio", 7);
        inventorySystem.takeOrder("TV", 8);
        inventorySystem.takeOrder("Computer", 9);
        inventorySystem.takeOrder("TV", 10);
        inventorySystem.takeOrder("Computer", 11);
        inventorySystem.takeOrder("TV", 12);

        inventorySystem.process();

        System.out.println(inventorySystem.report());
    }
}
