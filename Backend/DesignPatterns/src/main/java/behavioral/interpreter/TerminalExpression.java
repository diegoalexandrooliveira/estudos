package behavioral.interpreter;

import java.util.StringTokenizer;

public class TerminalExpression implements Expression {

    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        StringTokenizer stringTokenizer = new StringTokenizer(context);
        while (stringTokenizer.hasMoreElements()) {
            if (data.equals(stringTokenizer.nextToken())) {
                return true;
            }
        }
        return false;
    }
}
