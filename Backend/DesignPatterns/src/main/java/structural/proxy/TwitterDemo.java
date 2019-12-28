package structural.proxy;

public class TwitterDemo {

    public static void main(String[] args) {
        TwitterService twitter = (TwitterService) SecurityProxy.newInstance(new TwitterServiceStub());

        System.out.println(twitter.getTimeline("Test"));
    }
}
