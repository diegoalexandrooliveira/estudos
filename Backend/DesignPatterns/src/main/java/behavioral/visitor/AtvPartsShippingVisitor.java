package behavioral.visitor;

public class AtvPartsShippingVisitor implements AtvPartVisitor {

    double shippingAmount = 0;

    @Override
    public void visit(Oil oil) {
        shippingAmount += 15;
        System.out.println("Oil shipping price");
    }

    @Override
    public void visit(Wheel wheel) {
        shippingAmount += 2;
        System.out.println("Wheel shipping price");
    }
}
