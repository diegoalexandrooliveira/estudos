package behavioral.visitor;

public class Oil implements AtvPart {
    @Override
    public void accept(AtvPartVisitor atvPartVisitor) {
        atvPartVisitor.visit(this);
    }
}
