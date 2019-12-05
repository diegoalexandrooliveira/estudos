package creational.prototype;

public class PrototypeDemo {


    public static void main(String[] args) {
        Registry registry = new Registry();


        Movie movie = (Movie) registry.createItem("Movie");

        System.out.println(movie.toString());
    }
}
