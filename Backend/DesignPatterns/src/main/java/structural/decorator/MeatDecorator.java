package structural.decorator;

public class MeatDecorator extends SandwichDecorator {

    public MeatDecorator(Sandwich sandwich) {
        super(sandwich);
    }

    public String make() {
        return customSandwich.make() + addMeat();
    }

    private String addMeat() {
        return " + turkey";
    }
}
