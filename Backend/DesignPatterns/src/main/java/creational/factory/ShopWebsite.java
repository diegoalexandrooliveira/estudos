package creational.factory;

public class ShopWebsite extends Website {
    @Override
    protected void createWebsite() {
        addPage(new CartPage());
    }
}
