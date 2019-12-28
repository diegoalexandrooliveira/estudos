package creational.factory;

public class WebsiteFactory {


    public static Website getWebsite(WebsiteType siteType) {

        switch (siteType) {
            case BLOG: {
                return new BlogWebsite();
            }
            case SHOP: {
                return new ShopWebsite();
            }
            default: {
                return null;
            }
        }
    }

}
