package behavioral.visitor;

public interface AtvPartVisitor {
    void visit(Oil oil);

    void visit(Wheel wheel);
}
