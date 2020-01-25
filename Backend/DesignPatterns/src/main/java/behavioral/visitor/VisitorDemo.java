package behavioral.visitor;

public class VisitorDemo {

    public static void main(String[] args) {
        Oil oil = new Oil();
        Wheel wheel = new Wheel();
        AtvPartsShippingVisitor atvPartsShippingVisitor = new AtvPartsShippingVisitor();

        oil.accept(atvPartsShippingVisitor);
    }
}
