package behavioral.visitor;

public class Wheel implements AtvPart {
    @Override
    public void accept(AtvPartVisitor atvPartVisitor) {
        atvPartVisitor.visit(this);
    }
}
