package structural.decorator;

public class DressingDecorator extends SandwichDecorator {

    public DressingDecorator(Sandwich sandwich) {
        super(sandwich);
    }

    public String make() {
        return customSandwich.make() + addDressing();
    }

    private String addDressing() {
        return " + ketchup";
    }
}
