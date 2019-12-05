package creational.builder;

public class BuilderDemo {


    public static void main(String[] args) {

        LunchOrder lunchOrder = new LunchOrder.Builder()
                .bread("frances")
                .condiments("ketchup")
                .build();


        System.out.println(lunchOrder.toString());


    }
}
