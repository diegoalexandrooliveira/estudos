package behavioral.interpreter;

public class InterpreterDemo {

    static Expression buildInterpreterTree(){
        return new TerminalExpression("Lions");
    }

    public static void main(String[] args) {

        String context = "I like Lions";

        Expression expression = buildInterpreterTree();

        System.out.println(expression.interpret(context));

    }
}
