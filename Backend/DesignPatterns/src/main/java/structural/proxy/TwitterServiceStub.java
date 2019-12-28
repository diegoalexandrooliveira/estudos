package structural.proxy;

public class TwitterServiceStub implements TwitterService {
    @Override
    public String getTimeline(String screenName) {
        return "My timeline";
    }

    @Override
    public void postToTimeline(String screenName, String message) {

    }
}
