package creational.factory;

public class BlogWebsite extends Website {
    @Override
    protected void createWebsite() {
        addPage(new ContactPage());
    }
}
